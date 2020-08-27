package org.redcraft.redcraftreboot;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeTask extends BukkitRunnable {
    RedCraftReboot plugin;
    int remaining_seconds;
    int total_seconds;
    BossBar bossbar;
    int[] warns = new int[]{15, 10, 5, 4, 3, 2, 1};

    public TimeTask(RedCraftReboot plugin, int remaining_seconds)
    {
        bossbar = Bukkit.getServer().createBossBar("Server Restart", BarColor.RED, BarStyle.SOLID);

        for(Player p : Bukkit.getOnlinePlayers())
        {
            bossbar.addPlayer(p);
        }

        bossbar.setProgress(1.0d);
        bossbar.setVisible(true);
        
        this.plugin = plugin;
        this.remaining_seconds = remaining_seconds;
        this.total_seconds = remaining_seconds;
    }

    public void clearBossBar()
    {
        this.bossbar.setVisible(false);
    }
    @Override
    public void run() {
        
        if(this.remaining_seconds > 0)
        {
            bossbar.setProgress((double)(this.remaining_seconds)/(double)(this.total_seconds));

            //10s couter
            if(this.remaining_seconds < 10)
            {
                String message = Config.sCountdown.replace("%seconds%", String.valueOf(this.remaining_seconds));
                Bukkit.broadcastMessage(message);

                for(Player player : Bukkit.getServer().getOnlinePlayers())
                {
                    player.sendTitle(String.valueOf(this.remaining_seconds), "", 1,20, 1);
                }
            }

            //minute counter.
            if(this.remaining_seconds % 60 == 0)
            {
                for(int i : warns)
                {
                    if (this.remaining_seconds / 60 == i)
                    {
                        String message = Config.mCountdown.replace("%minutes%", String.valueOf(i));
                        Bukkit.broadcastMessage(message);
                    }
                }
            }
        }else
        {
            restart();
        }
        this.remaining_seconds--;
    }

   

    public void restart()
    {
        for(Player player : Bukkit.getServer().getOnlinePlayers())
        {
            player.kickPlayer(Config.kickMessage);
        }
        Bukkit.broadcastMessage("Shuting down");
        this.plugin.getServer().shutdown();
    }
}
