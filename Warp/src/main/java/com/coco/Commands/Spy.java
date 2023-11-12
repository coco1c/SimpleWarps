package com.coco.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spy implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("[Warp] Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("warp.spy") || !player.hasPermission("warp.*"))
            return true;
        if (args.length == 0) {
            player.sendMessage("Usage: /warpspy <Warp> ");
            return true;
        }
        return false;
    }
}
//todo finish this command
