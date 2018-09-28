package com.civservers.plugins.simplecommandblocker;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.help.HelpMap;
import org.bukkit.help.HelpTopic;


public class pluginCommandExecutor implements CommandExecutor {
	private final SimpleCommandBlocker plugin;
	
	private Utilities ut;
	
	public pluginCommandExecutor(SimpleCommandBlocker plugin) {
		this.plugin = plugin;
		ut = new Utilities(plugin);
	}
	
	@Override	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
    	if (cmd.getName().equalsIgnoreCase("simplecommandblocker")) {
    		if (args.length < 1) {
    			help(sender);
    			return false;
    			
    		} else if (args[0].equalsIgnoreCase("allow")){
    			if (sender.hasPermission("simplecommandblocker.admin") || sender.hasPermission("scb.admin")) {
    				if (args.length >= 2) {
    					SimpleCommandMap scm = new SimpleCommandMap(Bukkit.getServer());
    					
    					if (scm.getCommand(args[1]).equals(null)) {
    						ut.sendSender(sender, ChatColor.RED + plugin.msgs.get("not_a_command").toString().replaceAll("<cmd>", args[1].toString()));
    					} else {
    						Boolean tryAdd = ut.configListAdd("allowed_commands", args[1].toString());
    						if (tryAdd) {
    							ut.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("cmd_allowed").toString().replaceAll("<cmd>", args[1].toString()));
    							if (plugin.mcVer.contains("MC: 1.13") ) {
    								Bukkit.getServer().getOnlinePlayers().forEach(uPlayer -> {
	    								uPlayer.updateCommands();
	    							});
    							}
    						} else {
    							ut.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("already_allowed").toString().replaceAll("<cmd>", args[1].toString()));
    						}
    					}
    				} else {
    					help(sender);
    				}
    			} else {
    				ut.sendSender(sender,ChatColor.RED + plugin.msgs.get("no_perm").toString());
    				return false;
    			}
    			
    		} else if (args[0].equalsIgnoreCase("block")){
    			if (sender.hasPermission("simplecommandblocker.admin") || sender.hasPermission("scb.admin")) {
    				if (args.length >= 2) {
						Boolean tryRem = ut.configListRemove("allowed_commands", args[1].toString());
						if (tryRem) {
							ut.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("cmd_blocked").toString().replaceAll("<cmd>", args[1].toString()));
							if (plugin.mcVer.contains("MC: 1.13")) {
								Bukkit.getServer().getOnlinePlayers().forEach(uPlayer -> {
									uPlayer.updateCommands();
								});
							}
						} else {
							ut.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("already_blocked").toString().replaceAll("<cmd>", args[1].toString()));
						}
    				} else {
    					help(sender);
    				}
    			} else {
    				ut.sendSender(sender,ChatColor.RED + plugin.msgs.get("no_perm").toString());
    				return false;
    			}
    		
    		} else if (args[0].equalsIgnoreCase("reload")){
    			if (sender.hasPermission("simplecommandblocker.admin") || sender.hasPermission("scb.admin")) {
	    			boolean rStatus = plugin.reload();
	    			if (rStatus) {
	    				if (plugin.mcVer.contains("MC: 1.13") ) {
							Bukkit.getServer().getOnlinePlayers().forEach(uPlayer -> {
								uPlayer.updateCommands();
							});
	    				}
	    				ut.sendSender(sender,ChatColor.GREEN + " Reloaded!");
	    			} else {
	    				ut.sendSender(sender,ChatColor.RED + " Reload Failed!");
	    			}
	    			return true;
    			} else {
    				ut.sendSender(sender,ChatColor.RED + plugin.msgs.get("no_perm").toString());
    				return false;
    			}
    			
    			
			} else if (args[0].equalsIgnoreCase("trust")) {
				if (sender.hasPermission("simplecommandblocker.admin") || sender.hasPermission("scb.admin")) {
					if (args.length >= 2) {
    					String t_uuid = ut.getPreviousUUID(args[1]);
    					if (t_uuid.equals("not found")) {
    						ut.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("player_not_found").toString().replaceAll("<playername>", args[1]));
    					} else {
	    					Boolean addTrust = ut.configListAdd("trustlist", t_uuid);
	    					if (addTrust) {
	    						ut.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("add_trust").toString().replaceAll("<playername>", args[1]));
	    						if (plugin.mcVer.contains("MC: 1.13") ) {
	    							Bukkit.getServer().getOnlinePlayers().forEach(uPlayer -> {
	    								uPlayer.updateCommands();
	    							});
	    						}
	    					} else {
	    						ut.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("already_trust").toString().replaceAll("<playername>", args[1]));
	    					}
    					}
					} else {
						help(sender);
					}
				} else {
					ut.sendSender(sender,ChatColor.RED + plugin.msgs.get("no_perm").toString());
    				return false;
    			}
				
				
			} else if (args[0].equalsIgnoreCase("untrust")) {
				if (sender.hasPermission("simplecommandblocker.admin") || sender.hasPermission("scb.admin")) {
					if (args.length >= 2) {
    					String t_uuid = ut.getPreviousUUID(args[1]);
    					if (t_uuid.equals("not found")) {
    						ut.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("player_not_found").toString().replaceAll("<playername>", args[1]));
    					} else {
	    					Boolean remTrust = ut.configListRemove("trustlist", t_uuid);
	    					if (remTrust) {
	    						ut.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("remove_trust").toString().replaceAll("<playername>", args[1]));
	    						if (plugin.mcVer.contains("MC: 1.13") ) {
	    							Bukkit.getServer().getOnlinePlayers().forEach(uPlayer -> {
	    								uPlayer.updateCommands();
	    							});
	    						}
	    					} else {
	    						ut.sendSender(sender, ChatColor.GREEN + plugin.msgs.get("already_untrust").toString().replaceAll("<playername>", args[1]));
	    					}
    					}
					} else {
						help(sender);
					}
				} else {
					ut.sendSender(sender,ChatColor.RED + plugin.msgs.get("no_perm").toString());
    				return false;
    			}
				
			} else if (args[0].equalsIgnoreCase("trustlist")) {
				if (sender.hasPermission("simplecommandblocker.admin") || sender.hasPermission("scb.admin")) {
					List<String> trustList = plugin.config.getStringList("trustlist");
    				if (!trustList.isEmpty()) {
    					for (String t_uuid : trustList) {
    						ut.sendSender(sender, Bukkit.getOfflinePlayer(UUID.fromString(t_uuid)).getName().toString());
    					}
    				} else {
    					ut.sendSender(sender, plugin.msgs.get("empty_list").toString());
    				}

				} else {
					ut.sendSender(sender,ChatColor.RED + plugin.msgs.get("no_perm").toString());
    				return false;
    			}
				
		
				
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
		ut.sendSender(s,ChatColor.GREEN + plugin.msgs.get("help").toString());
	}
}
