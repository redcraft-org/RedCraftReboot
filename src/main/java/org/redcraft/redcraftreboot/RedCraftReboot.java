package org.redcraft.redcraftreboot;
import org.bukkit.plugin.java.JavaPlugin;

public class RedCraftReboot extends JavaPlugin {

	static public JavaPlugin instance;

	Reboot reboot;

	@Override
	public void onEnable() {
		instance = this;
		Config.readConfig(this);

		reboot = new Reboot(this);
		this.getCommand("reboot").setExecutor(reboot); 
		

	}

	@Override
	public void onDisable() {
		
	}

}