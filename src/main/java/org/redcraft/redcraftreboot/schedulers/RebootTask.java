package org.redcraft.redcraftreboot.schedulers;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.redcraft.redcraftreboot.Config;

public class RebootTask implements Runnable {
    int remainingSeconds;
    int totalSeconds;
    BossBar bossBar;
    int[] warns = new int[] { 15, 10, 5, 4, 3, 2, 1 };

    public RebootTask(int remainingSeconds) {
        bossBar = Bukkit.getServer().createBossBar(Config.bossBarTitle, BarColor.RED, BarStyle.SOLID);

        for (Player player : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(player);
        }

        bossBar.setProgress(1.0d);
        bossBar.setVisible(true);

        this.remainingSeconds = remainingSeconds;
        this.totalSeconds = remainingSeconds;
    }

    public void clearBossBar() {
        this.bossBar.setVisible(false);
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(player);
        }

        if (this.remainingSeconds > 0) {
            bossBar.setProgress((double) this.remainingSeconds / (double) this.totalSeconds);

            // 10s counter
            if (this.remainingSeconds < 10) {
                String message = Config.countdownMessageSeconds
                    .replace("%seconds%", String.valueOf(this.remainingSeconds));
                Bukkit.broadcastMessage(message);

                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    player.sendTitle(String.valueOf(this.remainingSeconds), "", 1, 20, 1);
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                }
            }

            // Minute counter
            if (this.remainingSeconds % 60 == 0) {
                for (int i : warns) {
                    if (this.remainingSeconds / 60 == i) {
                        String message = Config.countdownMessageMinutes.replace("%minutes%", String.valueOf(i));
                        Bukkit.broadcastMessage(message);
                    }
                }
            }
        } else {
            this.restart();
        }
        this.remainingSeconds--;
    }

    public void restart() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.kickPlayer(Config.rebootKickMessage);
        }

        Bukkit.getServer().shutdown();
    }
}
