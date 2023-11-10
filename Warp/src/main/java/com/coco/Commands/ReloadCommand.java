package com.coco.Commands;

import com.coco.Util.ChatColorUtil;
import com.coco.WarpMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    WarpMain main;
    public ReloadCommand(WarpMain main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (!sender.hasPermission("warp.reload") || !sender.hasPermission("warp.*"))
            return true;
        sender.sendMessage(ChatColorUtil.translateAlternateColorCodesAndHexes('&', "&aReloading Warp config..."));
        main.pluginConfig.reloadAndLoadConfig();
        sender.sendMessage(ChatColorUtil.translateAlternateColorCodesAndHexes('&', "&aConfig reloaded!"));
        return false;
    }
}
