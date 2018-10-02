package com.civservers.plugins.simplecommandblocker;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;



public class Listeners188 implements Listener {
	
	private SimpleCommandBlocker plugin;
	private Utilities ut;
	
	public Listeners188(SimpleCommandBlocker plugin) {
		this.plugin = plugin;
		ut = new Utilities(plugin);
	}


    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
    	Player player = e.getPlayer();

    	String p_uuid = player.getUniqueId().toString();
    	List<String> trustList = plugin.getConfig().getStringList("trustlist");
    	
    	if (!player.isOp() && !player.hasPermission("scb.bypass") && !player.hasPermission("simplecommandblocker.bypass") && !trustList.contains(p_uuid)) {
	    	if (plugin.getConfig().getBoolean("blockCommands")) {
	    		String cmd = "";
		    	List<String> allowedCommands = plugin.getConfig().getStringList("allowed_commands");
		    	if (e.getMessage().indexOf(" ") >= 0) {
		    		cmd = e.getMessage().substring(1, e.getMessage().indexOf(" "));
		    	} else {
		    		cmd = e.getMessage().substring(1, e.getMessage().length());
		    	}
		    	
		    	if (!allowedCommands.contains(cmd)) {
		    		ut.sendPlayer(player, ChatColor.RED + plugin.getMessages().get("no_cmds").toString().replaceAll("<cmd>", cmd));
		    		if (plugin.getConfig().getBoolean("play_sound"))
			    		try {
			    			player.playSound(player.getLocation(), Sound.valueOf(plugin.getConfig().getString("sound").toString()), 2, 1);
			    		}
			    		catch (Exception err) {
			    			ut.sendConsole(ChatColor.RED + plugin.getMessages().get("bad_sound_config").toString());
			    		}
		    		e.setCancelled(true);
		    	}
	    	}
    	}
    }

}