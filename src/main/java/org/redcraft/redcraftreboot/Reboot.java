package org.redcraft.redcraftreboot;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reboot implements CommandExecutor {

    private  final RedCraftReboot plugin;
    TimeTask timeTask;

    public Reboot(RedCraftReboot plugin)
    {
        this.plugin = plugin;
       
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        


        if(args.length == 0)
        {
            //Reboot in 10s!
            timeTask = new TimeTask(this.plugin,10, "");
            timeTask.runTaskTimer(this.plugin, 0, 20);
            return true;
        }

        if(args.length == 1 || args.length == 2)
        {
            try
            {
                String reason = "";
                if(args.length == 2) reason = args[1];
                int time = Integer.parseInt(args[0]);

                if(time == 0)
                {
                    timeTask = new TimeTask(this.plugin,10, reason);
                }if(time > Config.consoleSchedulesMinutes)
            {
                commandSender.sendMessage("You must enter a number of minutes that is less that "+Config.consoleSchedulesMinutes+" minutes");
                commandSender.sendMessage("The delay of reboot is now "+Config.consoleSchedulesMinutes+" minutes");
                timeTask = new TimeTask(this.plugin,+Config.consoleSchedulesMinutes*60, reason);
            }
            else {
                timeTask = new TimeTask(this.plugin,time * 60, reason);
            }


                timeTask.runTaskTimer(this.plugin, 0, 20);
            }
            catch(NumberFormatException exception)
            {
                commandSender.sendMessage("You must enter the number of minutes as a first argument");
                return false;
            }


        }
        return false;
    }
}
