package com.civservers.plugins.simplecommandblocker;




import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;


public final class SimpleCommandBlocker extends JavaPlugin implements Listener {

	public String mcVer = Bukkit.getVersion();
	public FileConfiguration config = getConfig();
	public Map<String, Object> msgs = config.getConfigurationSection("messages").getValues(true);
	Utilities ut = new Utilities(this);
	
	
	@Override
    public void onEnable() {
		config.options().copyDefaults(true);
	    saveConfig();
	    reload();
	    
		
		Boolean keepLoading = true;
		if (mcVer.contains("MC: 1.13")) {
			ut.debug("Loading files for version 1.13.1");
			Bukkit.getServer().getPluginManager().registerEvents(new Listeners1131(this), this);
		} else if (mcVer.contains("MC: 1.12")) {
			ut.debug("Loading files for version 1.12.2");
			Bukkit.getServer().getPluginManager().registerEvents(new Listeners188(this), this);
		} else if (mcVer.contains("MC: 1.8")) {
			ut.debug("Loading files for version 1.8.8");
			Bukkit.getServer().getPluginManager().registerEvents(new Listeners188(this), this);
		} else {
			ut.debug("No matching version found.");
			ut.sendConsole(ChatColor.RED + "DISABLED: Server version not supported.");
			keepLoading = false;
		}
		
		if (keepLoading) {
		    
			this.getCommand("simplecommandblocker").setExecutor(new pluginCommandExecutor(this));
			
		} else {
			Bukkit.getPluginManager().disablePlugin(this);
		}
    }
    
    @Override
    public void onDisable() {

    }
   
    public boolean reload() {
		reloadConfig();
		config = getConfig();
		msgs = config.getConfigurationSection("messages").getValues(true);
		return true;     
    }
}

