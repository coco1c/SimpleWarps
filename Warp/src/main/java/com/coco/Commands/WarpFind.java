package com.coco.Commands;

import com.coco.WarpMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpFind implements CommandExecutor {
    WarpMain main;
    public WarpFind(WarpMain main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player player){
            if (!player.hasPermission("warp.find") || !player.hasPermission("warp.*"))
                return true;
            if (args.length == 0) {
                player.sendMessage("Usage: /warpfind <Warp>");
                return true;
            }
            if (args.length > 1) {
                player.sendMessage("Usage: /warpfind <Warp>");
                return true;
            }
            String name = args[0];
            if (!main.pluginConfig.checkWarp(name)) {
                player.sendMessage(main.pluginConfig.warpInvalidMessage(name));
                return true;
            }
            main.chat.sendMessage(player, "The warp location is: " + main.pluginConfig.getWarpLocation(name, "&e", "&e", "&e", "&e"));
            return true;
        }
        return false;
    }
}
