package org.redcraft.redcraftreboot;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    static public String rebootMinuteMessage;
    static public String rebootSecondMessage ;

    static public String defaultRebootMessage;
    static public String rebootConsoleMessage;
    
    static public String countdownMinute;
    static public String countdownSecond;    
    
    static public String kickMessage;
    static public String cancelMessage;

    static public int maxCooldownMinutes;

    static public void readConfig(JavaPlugin plugin) {

        plugin.saveDefaultConfig();
        
        FileConfiguration config = plugin.getConfig();

        rebootMinuteMessage = config.getString("reboot-minute-message");
        rebootSecondMessage = config.getString("reboot-second-message");

        defaultRebootMessage = config.getString("reboot-default-message");
        rebootConsoleMessage = config.getString("reboot-console-message");

        countdownMinute = config.getString("countdown-minute-message");
        countdownSecond = config.getString("countdown-second-message");

        kickMessage = config.getString("reboot-kick-message");
        cancelMessage = config.getString("reboot-cancel-message");

        maxCooldownMinutes = config.getInt("maxCooldown-minutes");
        
        System.out.println(maxCooldownMinutes);
    }
}