package org.redcraft.redcraftreboot;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    static public String rebootMessageMinutes = "%sender% asked for a reboot in %minutes% minutes for the following reason: %reason%";
    static public String rebootMessageSeconds = "%sender% asked for a reboot in %seconds% seconds for the following reason: %reason%";

    static public String countdownMessageMinutes = "The server is restarting in %minutes% minutes";
    static public String countdownMessageSeconds = "The server is restarting in %seconds% seconds";

    static public String bossBarTitle = "Reboot";

    static public String rebootCancelMessage = "Server reboot has been canceled";
    static public String rebootConsoleMessage = "An automated reboot has been scheduled in %minutes% minutes";
    static public String rebootDefaultMessage = "scheduled restart";

    static public String rebootKickMessage = "The server is restarting";

    static public int defaultCountdownSeconds = 10;
    static public int maxCountdownMinutes = 15;

    static public void readConfig(JavaPlugin plugin) {
        plugin.saveDefaultConfig();

        FileConfiguration config = plugin.getConfig();

        rebootMessageMinutes = Config.getColoredString(config, "reboot-message-minutes");
        rebootMessageSeconds = Config.getColoredString(config, "reboot-message-seconds");

        countdownMessageMinutes = Config.getColoredString(config, "countdown-message-minutes");
        countdownMessageSeconds = Config.getColoredString(config, "countdown-message-seconds");

        bossBarTitle = Config.getColoredString(config, "boss-bar-title");

        rebootCancelMessage = Config.getColoredString(config, "reboot-cancel-message");
        rebootConsoleMessage = Config.getColoredString(config, "reboot-console-message");
        rebootDefaultMessage = Config.getColoredString(config, "reboot-default-message");

        rebootKickMessage = Config.getColoredString(config, "reboot-kick-message");

        defaultCountdownSeconds = config.getInt("default-countdown-seconds");
        maxCountdownMinutes = config.getInt("max-countdown-minutes");
    }

    public static String getColoredString(FileConfiguration config, String configNode) {
        return ChatColor.translateAlternateColorCodes('&', config.getString(configNode));
    }
}
