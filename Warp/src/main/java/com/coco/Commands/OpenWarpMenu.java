package com.coco.Commands;

import com.coco.WarpMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenWarpMenu implements CommandExecutor {
    private WarpMain main;
    public OpenWarpMenu(com.coco.WarpMain main) {
        this.main = main;
    }
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[Warp] Only players can use this command!");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission("warp.menu") || !player.hasPermission("warp.*"))
            return true;
        main.warpMenu.openWarpMenu(player);
        return true;
    }
}
