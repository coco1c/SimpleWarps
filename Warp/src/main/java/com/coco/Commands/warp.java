package com.coco.Commands;

import com.coco.Warp;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class warp implements CommandExecutor {
    FileConfiguration config;
    Warp main;
    public warp(Warp main) {
        this.main = main;
        config = main.getConfig();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[Warp] Only players can use this command!");
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("warp.use"))
            return false;
        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /warp <warp>"));
            return false;
        }
        String name = args[0];
        if (!main.pluginConfig.checkWarp(name)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat warp does not exist!"));
            main.getLogger().info(main.pluginConfig.checkWarp(name) + "");
            return false;
        }
        player.teleport(main.pluginConfig.warpLocation(name));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully teleported to &e" + name + "&a!"));

        return false;
    }
}
