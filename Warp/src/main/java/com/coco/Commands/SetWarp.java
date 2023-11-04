package com.coco.Commands;

import com.coco.WarpMain;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Map;

public class SetWarp implements CommandExecutor {
    private FileConfiguration config;
    private WarpMain main;
    public SetWarp(WarpMain main) {
        this.main = main;
        config = main.getConfig();
    }
    Map<String, Location> warpLocations;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[Warp] Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("warp.set") || !player.hasPermission("warp.*"))
            return true;
        if (args.length == 0) {
            main.chat.sendMessage(player, "&cUsage: /setwarp <Warp>");
            return true;
        }
        String name = args[0];
        if (main.pluginConfig.checkWarp(name)) {
            main.chat.sendMessage(player, main.pluginConfig.warpExistsMessage(name));
            return true;
        }
        main.pluginConfig.setWarp(name, player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ(), player.getWorld().getName());
        main.chat.sendMessage(player, main.pluginConfig.warpSetMessage(name));

        return true;
    }
}
