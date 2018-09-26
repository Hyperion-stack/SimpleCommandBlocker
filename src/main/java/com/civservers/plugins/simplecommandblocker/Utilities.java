package com.civservers.plugins.simplecommandblocker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Utilities {	
	private SimpleCommandBlocker plugin;
	
	public Utilities(SimpleCommandBlocker plugin) {
		this.plugin = plugin;
	}
    
    public String getOnlineUUID(String username) {
    	String out = "not found";
    	Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
		for (Player opl : onlinePlayers) {
			if (opl.getName().equalsIgnoreCase(username)) {
				out = opl.getUniqueId().toString();
			}
		}
    	
    	return out;
    	
    }
    
    public String getPreviousUUID(String username) {
    	String out = "not found";
    	OfflinePlayer[] prevPlayers = Bukkit.getOfflinePlayers();
    	for (OfflinePlayer opl : prevPlayers) {
    		if (opl.getName().equalsIgnoreCase(username)) {
    			out = opl.getUniqueId().toString();
    			break;
    		}
    	}
    	return out;
    }
    
    public boolean configListAdd(String path, String value) {
    	List<String> confList = new ArrayList<String>();
    	confList = plugin.config.getStringList(path);
    	if (!confList.contains(value)) {
    		confList.add(value);
    		plugin.config.set(path, confList);
    		plugin.saveConfig();
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public boolean configListRemove(String path, String value) {
    	List<String> confList = new ArrayList<String>();
    	confList = plugin.config.getStringList(path);
    	if (confList.contains(value)) {
    		confList.remove(value);
    		plugin.config.set(path, confList);
    		plugin.saveConfig();
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void sendPlayer(Player player, String msg) {
    	player.sendMessage(ChatColor.YELLOW + plugin.msgs.get("prefix").toString() + " " + msg);
    }
    
    public void sendSender(CommandSender s, String msg) {
    	s.sendMessage(ChatColor.YELLOW + plugin.msgs.get("prefix").toString() + msg);
    }
        
    public void sendConsole(String msg) {
    	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + plugin.msgs.get("prefix").toString() + " " + msg);
    }
    
    public void debug(String dString) {
    	if (plugin.config.getBoolean("debug")) {
    		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + plugin.msgs.get("prefix").toString() + " [DEBUG] " + dString);
    	}
    }
}