package org.redcraft.redcraftreboot;

import org.bukkit.plugin.java.JavaPlugin;
import org.redcraft.redcraftreboot.commands.RebootCommand;

public class RedCraftReboot extends JavaPlugin {

	static public RedCraftReboot instance;

	RebootCommand rebootCommand = new RebootCommand();

	@Override
	public void onEnable() {
		instance = this;
		Config.readConfig(this);

		this.getCommand("reboot").setExecutor(rebootCommand);
	}

	@Override
	public void onDisable() {
		this.getServer().getScheduler().cancelTasks(this);
	}

	static public RedCraftReboot getInstance() {
		return instance;
	}

}
