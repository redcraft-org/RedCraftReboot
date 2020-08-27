package org.redcraft.redcraftreboot;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    static public int consoleSchedulesMinutes;
    static public String rebootMessage;
    static public String consoleMessage;
    static public String mCountdown;
    static public String sCountdown;    
    static public String rebootMessageSeconds;
    static public String defaultReason;
    static public String kickMessage;
    static public String cancelMessage;

    static public void readConfig(JavaPlugin plugin) {
        plugin.saveDefaultConfig();
        
        FileConfiguration config = plugin.getConfig();

        rebootMessage = config.getString("reboot-message");
        rebootMessageSeconds = config.getString("reboot-message-s");
        consoleSchedulesMinutes = config.getInt("console-scheduled-minutes");
        defaultReason = config.getString("default-reason");
        consoleMessage = config.getString("console-message");
        mCountdown = config.getString("minute-countdown");
        sCountdown = config.getString("second-countdown");
        kickMessage = config.getString("kick-message");
        cancelMessage = config.getString("cancel-message");
        
    }
}