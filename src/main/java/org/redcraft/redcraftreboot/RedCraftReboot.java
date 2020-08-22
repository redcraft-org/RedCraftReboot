package org.redcraft.redcraftreboot;

import org.bukkit.plugin.java.JavaPlugin;

public class RedCraftReboot extends JavaPlugin {

	static public JavaPlugin instance;

	@Override
	public void onEnable() {
		instance = this;
		Config.readConfig(this);
	}
}