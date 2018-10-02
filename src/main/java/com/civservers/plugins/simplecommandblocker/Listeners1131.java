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
	
	public Listeners1131(SimpleCommandBlocker plugin) {
		this.plugin = plugin;
	}
        
   @EventHandler
   public void onPlayerTab(PlayerCommandSendEvent e) {
	   
	   Player player = e.getPlayer();
	   String p_uuid = player.getUniqueId().toString();
	   List<String> trustList = plugin.getConfig().getStringList("trustlist");
	   Utilities.debug("Updating command list for " + player.getDisplayName().toString());
	   if (!player.isOp() && !player.hasPermission("scb.bypass") && !player.hasPermission("simplecommandblocker.bypass") && !trustList.contains(p_uuid)) {
		   if (plugin.getConfig().getBoolean("blockTabComplete")) {
			   List<String> allowedCommands = plugin.getConfig().getStringList("allowed_commands");
			   List<String> cmdList = new ArrayList<>();
			   e.getCommands().forEach(cmd -> {
				   if (!allowedCommands.contains(cmd.toString())) {
					   cmdList.add(cmd);			   
				   } else {
					   Utilities.debug("Skipping cmd: " + cmd);
				   }
			   });
			   e.getCommands().removeAll(cmdList);
		   }
	   }
   }






}