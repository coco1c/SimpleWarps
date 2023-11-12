package com.coco.Commands;

import com.coco.WarpMain;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SetWarp implements CommandExecutor {
    private WarpMain main;
    public SetWarp(WarpMain main) {
        this.main = main;
    }
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
        if (!checkArgs(args)){
            return true;
        }
        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("--update")){
                if (!main.pluginConfig.checkWarp(args[0])) {
                    main.chat.sendMessage(player, main.pluginConfig.warpInvalidMessage(args[0]));
                    return true;
                }
                Location location = player.getLocation();
                main.pluginConfig.updateWarp(args[0], location);
                main.chat.sendMessage(player, main.pluginConfig.warpUpdatedMessage(args[0]));
                return true;
            }
            if (args[1].equalsIgnoreCase("--middle")){
                if (!main.pluginConfig.checkWarp(args[0])) {
                    Location location = player.getLocation();
                    double x = location.getBlockX() + 0.5;
                    int y = location.getBlockY() + 1;
                    double z = location.getBlockZ() + 0.5;
                    main.pluginConfig.setWarp(args[0], x, y, z, location.getPitch(), location.getYaw(), location.getWorld().getName());
                    main.chat.sendMessage(player, main.pluginConfig.warpSetMessage(args[0]));
                    return true;
                }
                Location location = player.getLocation();
                double x = location.getBlockX() + 0.5;
                int y = location.getBlockY() + 1;
                double z = location.getBlockZ() + 0.5;
                main.pluginConfig.updateWarp(args[0], x, y, z, location.getWorld().getName());
                main.chat.sendMessage(player, main.pluginConfig.warpUpdatedMessage(args[0]));
                return true;
            }
        }
        String name = args[0];
        if (main.pluginConfig.checkWarp(name)) {
            main.chat.sendMessage(player, main.pluginConfig.warpExistsMessage(name));
            return true;
        }
        main.pluginConfig.setWarp(name, player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), player.getLocation().getPitch(), player.getLocation().getYaw(), player.getWorld().getName());
        main.chat.sendMessage(player, main.pluginConfig.warpSetMessage(name));

        return true;
    }
    private boolean checkArgs(String[] args) {
        if (args.length == 0) {
            return false;
        }
        if (args[0].startsWith("-")) {
            return false;
        }
        return true;
    }
}
