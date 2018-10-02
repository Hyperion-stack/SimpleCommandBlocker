package com.civservers.plugins.simplecommandblocker;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

public class Listeners1131 implements Listener {
	
	private SimpleCommandBlocker plugin;
	private Utilities ut;
	
	public Listeners1131(SimpleCommandBlocker plugin) {
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
        
   @EventHandler
   public void onPlayerTab(PlayerCommandSendEvent e) {
	   
	   Player player = e.getPlayer();
	   String p_uuid = player.getUniqueId().toString();
	   List<String> trustList = plugin.getConfig().getStringList("trustlist");
	   ut.debug("Updating command list for " + player.getDisplayName().toString());
	   if (!player.isOp() && !player.hasPermission("scb.bypass") && !player.hasPermission("simplecommandblocker.bypass") && !trustList.contains(p_uuid)) {
		   if (plugin.getConfig().getBoolean("blockTabComplete")) {
			   List<String> allowedCommands = plugin.getConfig().getStringList("allowed_commands");
			   List<String> cmdList = new ArrayList<>();
			   e.getCommands().forEach(cmd -> {
				   if (!allowedCommands.contains(cmd.toString())) {
					   cmdList.add(cmd);			   
				   } else {
					   ut.debug("Skipping cmd: " + cmd);
				   }
			   });
			   e.getCommands().removeAll(cmdList);
		   }
	   }
   }






}