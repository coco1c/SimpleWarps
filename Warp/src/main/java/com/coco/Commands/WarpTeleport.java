package com.coco.Commands;

import com.coco.Util.Timer;
import com.coco.WarpMain;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WarpTeleport implements CommandExecutor, TabCompleter {
    FileConfiguration config;
    WarpMain main;

    public WarpTeleport(WarpMain main) {
        this.main = main;
        config = main.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[Warp] Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            main.chat.sendMessage(player, "&cUsage: /warp <Warp>");
            return true;
        }
        if (!player.hasPermission("warp.teleport." + args[0]) || !player.hasPermission("warp.teleport.*") || !player.hasPermission("warp.*")) {
            return true;
        }
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("--force")) {
                if (player.hasPermission("warp.teleport" + args[0] + ".force") || player.hasPermission("warp.teleport.*.force") || player.hasPermission("warp.*")) {
                    main.pluginUtil.teleportPlayerToWarpForce(player, args[0]);
                    return true;
                }
                main.pluginUtil.teleportPlayerToWarpForce(player, args[0]);
                return true;
            }
            main.pluginUtil.teleportPlayerToWarpForce(player, args[0]);
            return true;
        } else {
            String name = args[0];
            main.pluginUtil.teleportPlayerToWarp(player, name);
            return true;
        }
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                List<String> completions = new ArrayList<>();
                Set<String> warpNames = main.pluginConfig.getAllWarpNames();
                for (String warpName : warpNames) {
                    if (warpName.toLowerCase().startsWith(args[0].toLowerCase())) {
                        completions.add(warpName);
                    }
                }
                return completions;
            }
        }
        return null;
    }

}
