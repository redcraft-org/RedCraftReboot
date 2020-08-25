package org.redcraft.redcraftreboot;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class TimeTask extends BukkitRunnable {

    RedCraftReboot plugin;
    int remaining_seconds;

    int[] warns = new int[]{15, 10, 5, 4, 3, 2, 1};

    public TimeTask(RedCraftReboot plugin, int remaining_seconds, String reason)
    {

        System.out.println(Config.rebootMessage);
        System.out.println(reason);
        this.plugin = plugin;
        this.remaining_seconds = remaining_seconds;
    }

    @Override
    public void run() {

        if(this.remaining_seconds > 0)
        {
            //10s couter
            if(this.remaining_seconds < 10)
            {
                Bukkit.broadcastMessage("RedCraft is restarting in "+this.remaining_seconds+" seconds");
            }
            //minute counter.
            if(this.remaining_seconds % 60 == 0)
            {
                for(int i : warns)
                {
                    if (this.remaining_seconds / 60 == i)
                    {
                        Bukkit.broadcastMessage("RedCraft is restarting in "+i+ " minutes");
                    }
                }
            }

            if(this.remaining_seconds == 0)
            {
                restart();
            }
            this.remaining_seconds--;
        }else
        {
            this.cancel();
        }
    }

   

    public void restart()
    {
        this.plugin.getServer().shutdown();
    }
}
