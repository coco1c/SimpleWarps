<h1 align="center">
  <img width=2500 height=160 src="https://github.com/coco1c/SimpleWarps/blob/main/Warp/images/SimpleWarps.png">
  
  SimpleWarps
</h1>

<p align="center">
  <b>A Minecraft warp plugin with unlimited features</b><br><br>
  <a href="#features">Features</a> •
  <a href="#getting-started">Getting Started</a> •
  <a href="#future-plans">Future Plans</a> •
  <a href="#showcases">Showcases</a>
</p>
<br>

The SimpleWarps plugin is a Minecraft plugin that enables players with the appropriate permissions to create public warps. Players with the warp permission can use the command `/warp (warp name)` to teleport to these locations. Admins have the additional ability to use `/warp (warp name) --force` to bypass the cooldown.

Users, if granted permission, can access the warp menu (/warps) and teleport to specified warps listed in the config file. Additionally, you have the option to choose the particles and sound that will accompany the teleportation process.


## Features
- Unlimited Warps
- Ability to force teleport without a cooldown using `--force` as a parameter in the command
- Support for Hex colors in all messages
- 'Warp All' command allows teleporting all players to a specific warp! You can use /warpall (warp name) --force to force teleport players. The sender won't be teleported, as players with the bypass permission 'warp.teleport.all.byPass' are exempt.


## Getting Started
Add this plugin to the plugins folder on your server, and then simply use the command /setwarp (warp name).
Afterward, grant the players you want the permission warp.teleport.(warp name) so they can teleport to that warp.
Once you've given all the players the necessary permissions, it's done. It's that easy!


## Future Plans
- Safety feature: If a warp is not safe and the player can get hurt during teleportation, provide a warning. Players can run a command to teleport anyway, or it won't work at all. The behavior can be configured in the config file.
- Ability to force teleport only one player at a time.
- Ability to allow/deny teleporting to a warp if the warp is located inside a WorldGuard region.
- Random teleport command.
- Home command and sethome.
- Spawn and setspawn.
- Admin GUI: Allow administrators to delete warps or change their names.
- Update command: Enable changing the location or name of existing warps.


  ## Showcases
Warp menu:![image](https://github.com/coco1c/SimpleWarps/blob/main/Warp/images/warpmenu1v2.png)
  



