package com.civservers.plugins.simplecommandblocker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Utilities {

	private static SimpleCommandBlocker plugin;
	
	public static void init(SimpleCommandBlocker plugin) {
		Utilities.plugin = plugin;
	}
    
    public static String getOnlineUUID(String username) {
    	String out = "not found";
    	Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
		for (Player opl : onlinePlayers) {
			if (opl.getName().equalsIgnoreCase(username)) {
				out = opl.getUniqueId().toString();
			}
		}
    	
    	return out;
    	
    }
    
    public static String getPreviousUUID(String username) {
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
    
    public static boolean configListAdd(String path, String value) {
    	List<String> confList;
    	confList = plugin.getConfig().getStringList(path);
    	if (!confList.contains(value)) {
    		confList.add(value);
    		plugin.getConfig().set(path, confList);
    		plugin.saveConfig();
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public static boolean configListRemove(String path, String value) {
    	List<String> confList;
    	confList = plugin.getConfig().getStringList(path);
    	if (confList.contains(value)) {
    		confList.remove(value);
    		plugin.getConfig().set(path, confList);
    		plugin.saveConfig();
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public static void sendPlayer(Player player, String msg) {
    	player.sendMessage(ChatColor.YELLOW + plugin.getMessages().get("prefix").toString() + " " + msg);
    }
    
    public static void sendSender(CommandSender s, String msg) {
    	s.sendMessage(ChatColor.YELLOW + plugin.getMessages().get("prefix").toString() + msg);
    }
        
    public static void sendConsole(String msg) {
    	Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + plugin.getMessages().get("prefix").toString() + " " + msg);
    }

    public static void debug(String dString) {
    	if (plugin.getConfig().getBoolean("debug")) {
    		Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + plugin.getMessages().get("prefix").toString() + " [DEBUG] " + dString);
    	}
    }
}