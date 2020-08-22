package org.redcraft.redcraftreboot;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    static public String rebootMessage;

    static public int consoleSchedulesMinutes;
    static public String consoleMessage;

    static public void readConfig(JavaPlugin plugin) {
        plugin.saveDefaultConfig();

        FileConfiguration config = plugin.getConfig();

        rebootMessage = config.getString("reboot-message");

        consoleSchedulesMinutes = config.getInt("reboot-schedules-minutes");
        consoleMessage = config.getString("reboot-message");
    }
}