package org.redcraft.redcraftreboot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;

public class Reboot implements CommandExecutor {

    private  final RedCraftReboot plugin;
    TimeTask timeTask;

    public Reboot(final RedCraftReboot plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s,
            final String[] args) {

        if(args.length > 0 && args[0].equals("cancel") && timeTask != null)
        {
            timeTask.clearBossBar();
            timeTask.cancel();
            Bukkit.broadcastMessage(Config.cancelMessage);
            return true;
        }

        // Default reboot in 10s.
        if (args.length == 0) {

            final String message = Config.rebootMessageSeconds
                            .replace("%player%", commandSender.getName())
                            .replace("%reason%", Config.defaultReason)
                            .replace("%seconds%", "10");
            Bukkit.broadcastMessage(message);
            
            timeTask = new TimeTask(this.plugin, 10);
            timeTask.runTaskTimer(this.plugin, 0l, 20l);
            return true;
        }
        if (args.length == 1 || args.length == 2) {
            try {
                int time = Integer.parseInt(args[0]);

                if (time == 0) {
                    timeTask = new TimeTask(this.plugin, 10);
                    timeTask.runTaskTimer(this.plugin, 0l, 20l);

                    final String message = Config.rebootMessageSeconds
                            .replace("%player%", commandSender.getName())
                            .replace("%reason%", Config.defaultReason)
                            .replace("%seconds%", "10");
                    Bukkit.broadcastMessage(message);
                    return true;
                }else if (time > Config.consoleSchedulesMinutes) {
                    time = Config.consoleSchedulesMinutes;
                    commandSender.sendMessage("You must enter a number of minutes that is less than " + Config.consoleSchedulesMinutes + " minutes");
                    commandSender.sendMessage("The delay of reboot is now " + Config.consoleSchedulesMinutes + " minutes");                    
                }

                if(time>0){
                    timeTask = new TimeTask(this.plugin, time * 60);
                    timeTask.runTaskTimer(this.plugin, 0l, 20l);

                    String reason = (args.length == 2 ) ? args[1] : Config.defaultReason;

                    final String message = Config.rebootMessage
                                .replace("%player%", commandSender.getName())
                                .replace("%reason%", reason)
                                .replace("%minutes%", String.valueOf(time));
                    Bukkit.broadcastMessage(message);
                    return true;
                }else
                {
                    return false;
                }
            } catch (final NumberFormatException exception) {
                if (args.length == 1) {
                    timeTask = new TimeTask(this.plugin, 10);
                    timeTask.runTaskTimer(this.plugin, 0l, 20l);
                    final String message = Config.rebootMessageSeconds
                            .replace("%player%", commandSender.getName())
                            .replace("%reason%", args[0])
                            .replace("%seconds%", "10");

                    Bukkit.broadcastMessage(message);
                    return true;
                }else{
                    commandSender.sendMessage("You must enter the number of minutes as a first argument");
                    return false;
                }
                
            }


        }
        return false;
    }

}
