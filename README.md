# SimpleCommandBlocker
**Description:** Easy to use command blocker.  This plugin can block ALL commands from any plugin.  Commands can be allowed by using commands in game or editing the config.yml manually.  Players can be given a permission node or trusted via the /scb trust command.

**Latest Version:** 1.1.0

**Tested On:** Spigot 1.13.1, Spigot 1.8.8, Spigot 1.12.2

## Commands

> Aliases: /simplecommandblocker or /scb
>	​	/scb allow <cmd> Allows all player to use a command.
>	​	/scb block <cmd> Removes command from allowed commands list.
>	​	/scb trust <player name> - Allows a specific player to use commands.
>	​	/scb untrust <player name> - Removes a player from the trusted list.
>	​	/scb trustlist - Displays a list of all players that you have trusted.
>	​	/scb reload - Reloads the config.yml file.


## Permissions
- simplecomamndblocker.bypass or scb.bypass - Allows a player to use bypass command blocking.
- simplecommandblocker.admin or scb.admin - Allows player to add/remove trusted players and allowed commands.


## Default Config File

```
# SimpleCommandBlocker by Civalo
# Spigot address:   Github: https://github.com/johnelder/SimpleCommandBlocker
# License: GNU Lesser General Public License v3
# SOUND WILL NOT PLAY UNLESS A VALID SOUND STRING ID IS LISTED IN THIS CONFIG.
# SEE GITHUB PAGE FOR VALID SOUND OPTIONS
debug: false
blockCommands: true
blockTabComplete: true
play_sound: true
sound: ENTITY_GHAST_HURT
allowed_commands:
- me
- help
- msg
messages:
   prefix: '[SimpleCommandBlocker]'
   cmd_blocked: 'The command <cmd> is no longer allowed.'
   cmd_allowed: 'The command <cmd> is now allowed.'
   already_allowed: 'The command <cmd> is already allowed.'
   already_blocked: 'The command <cmd> is already blocked.'
   no_cmds: 'You cannot use the <cmd> command at this time!'
   no_perm: 'You do not have permission to use that command!'
   add_trust: '<playername> has been trusted with commands.'
   remove_trust: '<playername> is now blocked from commands.'
   already_trust: '<playername> is already trusted with commands.'
   already_untrust: '<playername> was not found on the trust list.'
   empty_list: 'There are no players on the trust list.'
   player_not_found: 'Cannot find the player <playername>.'
   bad_sound_config: 'The sound option in config.yml is not a valid sound string id.'
   not_a_command: '<cmd> is not a known command.'
   help: |
      All players are blocked from commands by default. To allow, use trust command or permissions: simplecommandblocker.bypass or scb.bypass
      /scb allow <cmd> Allows all player to use a command.
      /scb block <cmd> Removes command from allowed commands list.
      /scb trust <player name> - Allows a specific player to use commands.
      /scb untrust <player name> - Removes a player from the trusted list.
      /scb trustlist - Displays a list of all players that you have trusted.
      /scb reload - Reloads the config.yml file.
      
      
```
## 1.13.1 Sound Options:
Valid sound options can be found here: [https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html)

## 1.12.2 Sound Options:
Valid sound options can be found here: [https://techunlimitedgroup.com/javadocs/bukkit-1.12.2-R0.1-20180712.012114-155/org/bukkit/Sound.html](https://techunlimitedgroup.com/javadocs/bukkit-1.12.2-R0.1-20180712.012114-155/org/bukkit/Sound.html)

## 1.8.8 Sound Options:
Valid sound options can be found here: [https://techunlimitedgroup.com/javadocs/bukkit-1.8.8-R0.1-20160221.082532-43/org/bukkit/Sound.html](https://techunlimitedgroup.com/javadocs/bukkit-1.8.8-R0.1-20160221.082532-43/org/bukkit/Sound.html)


## Support

We try to be available whenever possible on Discord at [https://discord.gg/TTdGjy9](https://discord.gg/TTdGjy9)

## Contributing
We appreciate any contributions.  
- Please make submissions via pull requests on GitHub. 
- Please licensed submissions under GNU Lesser General Public License v3.

## Links
**Spigot Resource Listing:** [https://www.spigotmc.org/resources/simplecommandblocker.61114/](https://www.spigotmc.org/resources/simplecommandblocker.61114/)

**Issues:** [https://github.com/johnelder/SimpleCommandBlocker/issues](https://github.com/johnelder/SimpleCommandBlocker/issues)

**Beta Testing Server:** techunlimitedgroup.com:25560

**Other Servers:** Many of our plugins can be found in game at pubcraft.civservers.com

