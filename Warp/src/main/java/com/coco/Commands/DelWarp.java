package com.coco.Commands;

import com.coco.WarpMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelWarp implements CommandExecutor {
    WarpMain main;
    public DelWarp(WarpMain main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("[Warp] Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("warp.del") || !player.hasPermission("warp.*"))
            return true;
        if (args.length == 0) {
            main.chat.sendMessage(player, "&cUsage: /delwarp <Warp> (optional: --all)");
            return true;
        }
        if (args.length > 1) {

            main.chat.sendMessage(player, "&cUsage: /delwarp <Warp> (optional: --all)");
            return true;
        }
        String name = args[0];
        if (name.equalsIgnoreCase("--all")) {
            if (!player.hasPermission("warp.del.all") || !player.hasPermission("warp.*")) {
                main.chat.sendMessage(player, "&cYou do not have permission to delete all warps!");
                return true;
            }
            main.pluginConfig.deleteAllWarps();
            main.chat.sendMessage(player, main.pluginConfig.delAllWarpsMessage());
            return true;
        }
        if (!main.pluginConfig.checkWarp(name)) {
            main.chat.sendMessage(player, main.pluginConfig.warpInvalidMessage(name));
            return true;
        }
        main.pluginConfig.deleteWarp(name);
        main.chat.sendMessage(player, main.pluginConfig.delWarpMessage(name));
        return false;
    }
}
