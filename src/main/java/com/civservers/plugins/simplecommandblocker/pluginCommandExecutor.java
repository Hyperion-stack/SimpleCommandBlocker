package com.civservers.plugins.simplecommandblocker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class pluginCommandExecutor implements CommandExecutor {
	private final SimpleCommandBlocker plugin;
	
	Utilities Util;
	
	public pluginCommandExecutor(SimpleCommandBlocker plugin) {
		this.plugin = plugin;
		Util = new Utilities(plugin);
	}
	
	@Override	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
    	if (cmd.getName().equalsIgnoreCase("simplecommandblocker")) {
    		if (args.length < 1) {
    			help(sender);
    			return false;
    			
    		} else if (args[0].equalsIgnoreCase("allow")){
    			if (sender.hasPermission("simpletag.admin")) {
    				if (args.length >= 2) {
    					if (Bukkit.getServer().getPluginCommand(args[1].toString()).equals(null)) {
    						Util.sendSender(sender, ChatColor.RED + plugin.msgs.get("not_a_command").toString().replaceAll("<cmd>", args[1].toString()));
    					} else {
    						Boolean tryAdd = Util.configListAdd("allowed_commands", args[1].toString());
    						if (tryAdd) {
    							Util.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("cmd_allowed").toString().replaceAll("<cmd>", args[1].toString()));
    						} else {
    							Util.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("already_allowed").toString().replaceAll("<cmd>", args[1].toString()));
    						}
    					}
    				} else {
    					help(sender);
    				}
    			} else {
    				Util.sendSender(sender,ChatColor.RED + plugin.msgs.get("no_perm").toString());
    				return false;
    			}
    			
    		} else if (args[0].equalsIgnoreCase("block")){
    			if (sender.hasPermission("simpletag.admin")) {
    				if (args.length >= 2) {
						Boolean tryRem = Util.configListRemove("allowed_commands", args[1].toString());
						if (tryRem) {
							Util.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("cmd_blocked").toString().replaceAll("<cmd>", args[1].toString()));
						} else {
							Util.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("already_blocked").toString().replaceAll("<cmd>", args[1].toString()));
						}
    				} else {
    					help(sender);
    				}
    			} else {
    				Util.sendSender(sender,ChatColor.RED + plugin.msgs.get("no_perm").toString());
    				return false;
    			}
    		
    		} else if (args[0].equalsIgnoreCase("reload")){
    			if (sender.hasPermission("simpletag.admin")) {
	    			boolean rStatus = plugin.reload();
	    			if (rStatus) {
	    				Util.sendSender(sender,ChatColor.GREEN + " Reloaded!");
	    			} else {
	    				Util.sendSender(sender,ChatColor.RED + " Reload Failed!");
	    			}
	    			return true;
    			} else {
    				Util.sendSender(sender,ChatColor.RED + plugin.msgs.get("no_perm").toString());
    				return false;
    			}
    			
    			
			} else if (args[0].equalsIgnoreCase("trust")) {
				if (sender.hasPermission("simpletag.admin")) {
					if (args.length >= 2) {
    					String t_uuid = Util.getOnlineUUID(args[1]);
    					if (t_uuid.equals("not found")) {
    						Util.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("player_not_found").toString().replaceAll("<playername>", args[1]));
    					} else {
	    					Boolean addTrust = Util.configListAdd("trustlist", t_uuid);
	    					if (addTrust) {
	    						Util.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("add_trust").toString().replaceAll("<playername>", args[1]));
	    					} else {
	    						Util.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("already_trust").toString().replaceAll("<playername>", args[1]));
	    					}
    					}
					} else {
						help(sender);
					}
				} else {
					Util.sendSender(sender,ChatColor.RED + plugin.msgs.get("no_perm").toString());
    				return false;
    			}
				
				
			} else if (args[0].equalsIgnoreCase("untrust")) {

				
				
				
				
				
				
				
				
				
				
				
    		} else {
    			help(sender);
        		return false;
    		}
		} else {
			return false;
		}
		return false;
	}
	
	public void help(CommandSender s) {
		Util.sendSender(s,ChatColor.GREEN + plugin.msgs.get("help").toString());
	}
}
