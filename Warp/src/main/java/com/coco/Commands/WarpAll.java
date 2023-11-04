package com.coco.Commands;

import com.coco.WarpMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpAll implements CommandExecutor {
    private WarpMain main;
    public WarpAll(WarpMain main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("[Warp] Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("warp.teleport.all") || !player.hasPermission("warp.*"))
            return true;
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("--force")) {
                if (!main.pluginConfig.checkWarp(args[0])) {
                    main.chat.sendMessage(player, main.pluginConfig.warpInvalidMessage(args[0]));
                    return true;
                }
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p == player){
                        main.chat.sendMessage(p, "&cYou will not be teleported to &e" + args[0] + "&c because you executed the command!");
                        continue;
                    }
                    if (p.hasPermission("warp.teleport.all.byPass")) {
                        main.chat.sendMessage(p, "&cYou will not be teleported to &e" + args[0] + "&c because you have the permission &ewarp.all.byPass&c!");
                        continue;
                    }
                    main.pluginUtil.teleportPlayerToWarpForce(p, args[0]);
                }
                return false;
            }
        }
        if (args.length != 1) {
            main.chat.sendMessage(player, "&cUsage: /warpall <Warp>");
            return true;
        }

        if (!main.pluginConfig.checkWarp(args[0])) {
            main.chat.sendMessage(player, main.pluginConfig.warpInvalidMessage(args[0]));
            return true;
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p == player){
                main.chat.sendMessage(p, "&cYou will not be teleported to &e" + args[0] + "&c because you executed the command!");
                main.chat.sendMessage(p, "&cIf you want to force teleport everyone to &e" + args[0] + "&c, use &e/warpall <Warp> --force&c!");
                continue;
            }
            if (p.hasPermission("warp.teleport.all.byPass")) {
                main.chat.sendMessage(p, "&cYou will not be teleported to &e" + args[0] + "&c because you have the permission &ewarp.all.byPass&c!");
                continue;
            }
            main.pluginUtil.teleportPlayerToWarp(p, args[0]);
        }
        return false;
    }
}
