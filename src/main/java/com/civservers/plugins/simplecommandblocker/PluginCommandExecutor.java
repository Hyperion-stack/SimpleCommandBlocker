package com.civservers.plugins.simplecommandblocker;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;

import java.util.List;
import java.util.UUID;


public class PluginCommandExecutor implements CommandExecutor {

    private final SimpleCommandBlocker plugin;

    public PluginCommandExecutor(SimpleCommandBlocker plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("simplecommandblocker")) {
            if(args.length < 1) {
                help(sender);
                return false;

            } else if(args[0].equalsIgnoreCase("allow")) {
                if(!sender.hasPermission("simplecommandblocker.admin") || !sender.hasPermission("scb.admin")) {
                    Utilities.sendSender(sender, ChatColor.RED + plugin.getMessages().get("no_perm").toString());
                    return true;
                }
                if(args.length < 2) {
                    help(sender);
                    return true;
                }
                SimpleCommandMap scm = new SimpleCommandMap(Bukkit.getServer());

                if(scm.getCommand(args[1]).equals(null)) {
                    Utilities.sendSender(sender, ChatColor.RED + plugin.getMessages().get("not_a_command").toString().replaceAll("<cmd>", args[1]));
                } else {
                    boolean tryAdd = Utilities.configListAdd("allowed_commands", args[1]);
                    if(tryAdd) {
                        Utilities.sendSender(sender, ChatColor.GREEN + plugin.getMessages().get("cmd_allowed").toString().replaceAll("<cmd>", args[1]));
                        if(plugin.getMinecraftVersion().contains("MC: 1.13")) {
                            Bukkit.getServer().getOnlinePlayers().forEach(uPlayer -> {
                                uPlayer.updateCommands();
                            });
                        }
                    } else {
                        Utilities.sendSender(sender, ChatColor.GREEN + plugin.getMessages().get("already_allowed").toString().replaceAll("<cmd>", args[1]));
                    }
                }

            } else if(args[0].equalsIgnoreCase("block")) {
                if(!sender.hasPermission("simplecommandblocker.admin") || !sender.hasPermission("scb.admin")) {
                    Utilities.sendSender(sender, ChatColor.RED + plugin.getMessages().get("no_perm").toString());
                    return true;
                }
                if(args.length < 2) {
                    help(sender);
                    return true;
                }
                boolean tryRem = Utilities.configListRemove("allowed_commands", args[1]);
                if(tryRem) {
                    Utilities.sendSender(sender, ChatColor.GREEN + plugin.getMessages().get("cmd_blocked").toString().replaceAll("<cmd>", args[1]));
                    if(plugin.getMinecraftVersion().contains("MC: 1.13")) {
                        Bukkit.getServer().getOnlinePlayers().forEach(uPlayer -> {
                            uPlayer.updateCommands();
                        });
                    }
                } else {
                    Utilities.sendSender(sender, ChatColor.GREEN + plugin.getMessages().get("already_blocked").toString().replaceAll("<cmd>", args[1]));
                }

            } else if(args[0].equalsIgnoreCase("reload")) {
                if(!sender.hasPermission("simplecommandblocker.admin") || !sender.hasPermission("scb.admin")) {
                    Utilities.sendSender(sender, ChatColor.RED + plugin.getMessages().get("no_perm").toString());
                    return true;
                }
                boolean rStatus = plugin.reload();
                if(rStatus) {
                    if(plugin.getMinecraftVersion().contains("MC: 1.13")) {
                        Bukkit.getServer().getOnlinePlayers().forEach(uPlayer -> {
                            uPlayer.updateCommands();
                        });
                    }
                    Utilities.sendSender(sender, ChatColor.GREEN + " Reloaded!");
                } else {
                    Utilities.sendSender(sender, ChatColor.RED + " Reload Failed!");
                }
                return true;
            } else if(args[0].equalsIgnoreCase("trust")) {
                if(!sender.hasPermission("simplecommandblocker.admin") || !sender.hasPermission("scb.admin")) {
                    Utilities.sendSender(sender, ChatColor.RED + plugin.getMessages().get("no_perm").toString());
                    return true;
                }
                if(args.length < 2) {
                    help(sender);
                    return true;
                }
                String t_uuid = Utilities.getPreviousUUID(args[1]);
                if(t_uuid.equals("not found")) {
                    Utilities.sendSender(sender, ChatColor.GREEN + plugin.getMessages().get("player_not_found").toString().replaceAll("<playername>", args[1]));
                } else {
                    boolean addTrust = Utilities.configListAdd("trustlist", t_uuid);
                    if(addTrust) {
                        Utilities.sendSender(sender, ChatColor.GREEN + plugin.getMessages().get("add_trust").toString().replaceAll("<playername>", args[1]));
                        if(plugin.getMinecraftVersion().contains("MC: 1.13")) {
                            Bukkit.getServer().getOnlinePlayers().forEach(uPlayer -> {
                                uPlayer.updateCommands();
                            });
                        }
                    } else {
                        Utilities.sendSender(sender, ChatColor.GREEN + plugin.getMessages().get("already_trust").toString().replaceAll("<playername>", args[1]));
                    }
                }
            } else if(args[0].equalsIgnoreCase("untrust")) {
                if(!sender.hasPermission("simplecommandblocker.admin") || !sender.hasPermission("scb.admin")) {
                    Utilities.sendSender(sender, ChatColor.RED + plugin.getMessages().get("no_perm").toString());
                    return true;
                }
                if(args.length < 2) {
                    help(sender);
                    return true;
                }
                String t_uuid = Utilities.getPreviousUUID(args[1]);
                if(t_uuid.equals("not found")) {
                    Utilities.sendSender(sender, ChatColor.GREEN + plugin.getMessages().get("player_not_found").toString().replaceAll("<playername>", args[1]));
                } else {
                    boolean remTrust = Utilities.configListRemove("trustlist", t_uuid);
                    if(remTrust) {
                        Utilities.sendSender(sender, ChatColor.GREEN + plugin.getMessages().get("remove_trust").toString().replaceAll("<playername>", args[1]));
                        if(plugin.getMinecraftVersion().contains("MC: 1.13")) {
                            Bukkit.getServer().getOnlinePlayers().forEach(uPlayer -> {
                                uPlayer.updateCommands();
                            });
                        }
                    } else {
                        Utilities.sendSender(sender, ChatColor.GREEN + plugin.getMessages().get("already_untrust").toString().replaceAll("<playername>", args[1]));
                    }
                }
            } else if(args[0].equalsIgnoreCase("trustlist")) {
                if(!sender.hasPermission("simplecommandblocker.admin") || !sender.hasPermission("scb.admin")) {
                    Utilities.sendSender(sender, ChatColor.RED + plugin.getMessages().get("no_perm").toString());
                    return true;
                }
                List<String> trustList = plugin.getConfig().getStringList("trustlist");
                if(!trustList.isEmpty()) {
                    for(String t_uuid : trustList) {
                        Utilities.sendSender(sender, Bukkit.getOfflinePlayer(UUID.fromString(t_uuid)).getName());
                    }
                } else {
                    Utilities.sendSender(sender, plugin.getMessages().get("empty_list").toString());
                }
            } else {
                help(sender);
                return true;
            }
        }
        return false;
    }

    private void help(CommandSender s) {
        Utilities.sendSender(s, ChatColor.GREEN + plugin.getMessages().get("help").toString());
    }
}
