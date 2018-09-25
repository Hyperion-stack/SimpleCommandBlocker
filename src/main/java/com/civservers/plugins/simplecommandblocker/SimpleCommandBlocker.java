package com.civservers.plugins.simplecommandblocker;



import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;




public final class SimpleCommandBlocker extends JavaPlugin implements Listener {

	public String pluginName = "SimpleCommandBlocker";
	public FileConfiguration config = getConfig();
	public Map<String, Object> msgs = config.getConfigurationSection("messages").getValues(true);
	Utilities Util = new Utilities(this);
	
	
	@Override
    public void onEnable() {
		
		config.options().copyDefaults(true);
	    saveConfig();
	    
		this.getCommand("simplecommandblocker").setExecutor(new pluginCommandExecutor(this));
		
		Bukkit.getPluginManager().registerEvents(this, this);
		reload();
    }
    
    @Override
    public void onDisable() {

    }
    
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
    	Player player = e.getPlayer();
    	if (!player.isOp() && !player.hasPermission("scb.bypass") && !player.hasPermission("simplecommandblocker.bypass")) {
	    	if (config.getBoolean("blockCommands")) {
	    		String cmd = "";
		    	List<String> allowedCommands = config.getStringList("allowedCommands");
		    	if (e.getMessage().indexOf(" ") >= 0) {
		    		cmd = e.getMessage().substring(1, e.getMessage().indexOf(" "));
		    	} else {
		    		cmd = e.getMessage().substring(1, e.getMessage().length());
		    	}
		    	
		    	if (!allowedCommands.contains(cmd)) {
		    		Util.sendPlayer(player, ChatColor.RED + msgs.get("no_cmds").toString());
		    		if (config.getBoolean("play_sound"))
			    		try {
			    			player.playSound(player.getLocation(), Sound.valueOf(config.getString("sound").toString()), 2, 1);
			    		}
			    		catch (Exception err) {
			    			Util.sendConsole(ChatColor.RED + msgs.get("bad_sound_config").toString());
			    		}
		    		e.setCancelled(true);
		    	}
	    	}
    	}
    }
        
   

    public boolean reload() {
		reloadConfig();
		config = getConfig();
		msgs = config.getConfigurationSection("messages").getValues(true);
		return true;     
    }
}

