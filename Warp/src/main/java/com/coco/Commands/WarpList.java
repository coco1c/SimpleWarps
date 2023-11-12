package com.coco.Commands;

import com.coco.WarpMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpList implements CommandExecutor {
    WarpMain main;
    public WarpList(WarpMain main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player player){
            if (!player.hasPermission("warp.list") || !player.hasPermission("warp.*"))
                return true;
            main.chat.sendMessage(player, "&7- &fWarp Name &7- &fX &7- &fY &7- &fZ &7- &fWorld");
            main.chat.sendMessage(player, "&7_________________________________________________");
            for (String warp : main.pluginConfig.getAllWarps()) {
                main.chat.sendMessage(player, "&7- &f" + warp + " &7- &f" + main.pluginConfig.getWarpX(warp) + " &7- &f" + main.pluginConfig.getWarpY(warp) + " &7- &f" + main.pluginConfig.getWarpZ(warp) + " &7- &f" + main.pluginConfig.getWarpWorld(warp));
            }
            main.chat.sendMessage(player, "&7_________________________________________________");
            return true;

        }
        if (!(sender instanceof Player)){
            main.pluginUtil.sendConsoleMessage("- Warp Name - X - Y - Z - World");
            main.pluginUtil.sendConsoleMessage("_________________________________________________");
            for (String warp : main.pluginConfig.getAllWarps()) {
                main.pluginUtil.sendConsoleMessage("- " + warp + " - " + main.pluginConfig.getWarpX(warp) + " - " + main.pluginConfig.getWarpY(warp) + " - " + main.pluginConfig.getWarpZ(warp) + " - " + main.pluginConfig.getWarpWorld(warp));
            }
            main.pluginUtil.sendConsoleMessage("_________________________________________________");
            return true;
        }
        return false;
    }
}
