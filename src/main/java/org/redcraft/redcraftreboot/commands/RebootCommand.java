package org.redcraft.redcraftreboot.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.redcraft.redcraftreboot.Config;
import org.redcraft.redcraftreboot.RedCraftReboot;
import org.redcraft.redcraftreboot.schedulers.RebootTask;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class RebootCommand implements CommandExecutor {

    RebootTask rebootTask = null;

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String label, final String[] args) {
        // Cancel the reboot is there is already a task for it.
        if (args.length > 0 && args[0].equals("cancel")) {
            return this.cancelReboot(commandSender);
        }

        if (rebootTask != null) {
            commandSender.sendMessage("A reboot has already been scheduled");
            return false;
        }

        int secondsBeforeReboot = this.getSecondsBeforeReboot(args);

        if (secondsBeforeReboot < 0) {
            String message = String.format("The time must be between 1 and %s minutes", Config.maxCountdownMinutes);
            commandSender.sendMessage(message);
            return false;
        }

        String rebootReason = this.getRebootReason(args);

        String sender = ChatColor.DARK_RED + commandSender.getName();

        if (commandSender instanceof Player) {
            sender = ((Player) commandSender).getDisplayName();
        }

        String rebootMessage = secondsBeforeReboot > 60 ? Config.rebootMessageMinutes : Config.rebootMessageSeconds;

        rebootMessage = rebootMessage
            .replace("%sender%", sender)
            .replace("%reason%", rebootReason)
            .replace("%seconds%", String.valueOf(secondsBeforeReboot))
            .replace("%minutes%", String.valueOf(secondsBeforeReboot / 60));

        RedCraftReboot plugin = RedCraftReboot.getInstance();
        rebootTask = new RebootTask(secondsBeforeReboot);
        Bukkit.getServer().getScheduler().runTaskTimer(plugin, rebootTask, 0l, 20l);

        Bukkit.broadcastMessage(rebootMessage);

        return true;
    }

    public int getSecondsBeforeReboot(String[] args) {
        int secondsBeforeReboot = Config.defaultCountdownSeconds;

        // If an argument is given, parse it as time
        if (args.length > 0) {
            try {
                secondsBeforeReboot = Integer.parseInt(args[0]) * 60;
            } catch (NumberFormatException exception) {
                return -1; // error
            }
        }

        if (secondsBeforeReboot > Config.maxCountdownMinutes * 60) {
            return -2; // error
        }

        return secondsBeforeReboot;
    }

    public String getRebootReason(String[] args) {
        String rebootReason = Config.rebootDefaultMessage;

        if (args.length > 1) {
            rebootReason = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        }

        return rebootReason;
    }

    public boolean cancelReboot(CommandSender commandSender) {
        if (rebootTask != null) {
            rebootTask.clearBossBar();

            RedCraftReboot plugin = RedCraftReboot.getInstance();
            Bukkit.getServer().getScheduler().cancelTasks(plugin);

            Bukkit.broadcastMessage(Config.rebootCancelMessage);

            rebootTask = null;
            return true;
        }

        commandSender.sendMessage("There is no reboot to cancel");
        return false;
    }

}
