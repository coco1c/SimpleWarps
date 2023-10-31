package com.coco.Commands;

import com.coco.Warp;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class setwarp implements CommandExecutor {
    private FileConfiguration config;
    private Warp main;
    public setwarp(Warp main) {
        this.main = main;
        config = main.getConfig();
    }
    Map<String, Location> warpLocations;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[Warp] Only players can use this command!");
            return false;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("setwarp.use"))
            return false;
        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cUsage: /setwarp <warp>"));
            return false;
        }
        String name = args[0];
        if (main.pluginConfig.checkWarp(name)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThat warp already exists!"));
            return false;
        }
        main.pluginConfig.setWarp(name, player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ(), player.getWorld().getName());
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully created warp &e" + name + "&a!"));

        return false;
    }
}
