package org.redcraft.redcraftreboot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;

public class Reboot implements CommandExecutor {

    private  final RedCraftReboot plugin;
    TimeTask timeTask;

    public Reboot(final RedCraftReboot plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s,
            final String[] args) {
        //Cancel the reboot is there is already a task for it.
        if(args.length > 0 && args[0].equals("cancel") && timeTask != null)
        {
            timeTask.clearBossBar();
            timeTask.cancel();
            Bukkit.broadcastMessage(Config.cancelMessage);
            return true;
        }
        // If no arguments are provided,the server is restarted with the default reboot message.
        if (args.length == 0) {
            
            
            final String message = Config.rebootSecondMessage 
                            .replace("%player%", commandSender.getName())
                            .replace("%reason%", Config.defaultRebootMessage)
                            .replace("%seconds%", "10");
            Bukkit.broadcastMessage(message);
            
            //Display the countdown to the players.
            timeTask = new TimeTask(this.plugin, 10);
            timeTask.runTaskTimer(this.plugin, 0l, 20l);
            return true;
        }
        //If 1 or 2 arguments(time+reason) are given then we parse them.
        if (args.length == 1 || args.length == 2) {
            try {
                int time = Integer.parseInt(args[0]);
                //the minimum reboot cooldown must be greater than 10s.
                if (time == 0) {
                    timeTask = new TimeTask(this.plugin, 10);
                    timeTask.runTaskTimer(this.plugin, 0l, 20l);

                    final String message = Config.rebootSecondMessage 
                            .replace("%player%", commandSender.getName())
                            .replace("%reason%", Config.defaultRebootMessage)
                            .replace("%seconds%", "10");
                    Bukkit.broadcastMessage(message);
                    return true;
                //the maximum reboot cooldown must be smaller than maxColdownMinutes.
                }else if (time > Config.maxCooldownMinutes) {
                    time = Config.maxCooldownMinutes;
                    commandSender.sendMessage("You must enter a number of minutes that is less than " + Config.maxCooldownMinutes + " minutes");
                    commandSender.sendMessage("The delay of reboot is now " + Config.maxCooldownMinutes + " minutes");                    
                }

                if(time>0){
                    //convert minutes to seconds.
                    timeTask = new TimeTask(this.plugin, time * 60);
                    //run boosbar display + countdown.
                    timeTask.runTaskTimer(this.plugin, 0l, 20l);

                    String reason = (args.length == 2 ) ? args[1] : Config.defaultRebootMessage;
                    
                    final String message = Config.rebootMinuteMessage
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
                    final String message = Config.rebootSecondMessage 
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
