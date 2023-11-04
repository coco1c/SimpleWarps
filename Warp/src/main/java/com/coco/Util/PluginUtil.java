package com.coco.Util;

import com.coco.WarpMain;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PluginUtil implements PluginUtilInterface{
    private WarpMain main;
    public PluginUtil(WarpMain main) {
        this.main = main;
    }

    @Override
    public void teleportPlayerToWarp(Player player, String warpName) {
        if (!main.pluginConfig.checkWarp(warpName)) {
            main.chat.sendMessage(player, main.pluginConfig.warpInvalidMessage(warpName));
            return;
        }
        main.chat.sendMessage(player, main.pluginConfig.warpInProgressMessage(warpName));
        Location playerLocation = player.getLocation();
        Timer timer = new Timer(main);

        timer.startTimer(main.pluginConfig.teleportCooldown(), () -> {
            player.teleport(main.pluginConfig.warpLocation(warpName));
            main.chat.sendMessage(player, main.pluginConfig.warpTeleportMessageAfterTeleport(warpName));
        }, () -> {
            if (player.getLocation().getBlockX() != playerLocation.getBlockX() || player.getLocation().getBlockY() != playerLocation.getBlockY() || player.getLocation().getBlockZ() != playerLocation.getBlockZ()) {
                main.chat.sendMessage(player, main.pluginConfig.warpCancelledMessage());
                timer.cancelTimer();
            }
        });
    }

    @Override
    public void teleportPlayerToWarpForce(Player player, String warpName) {
        if (!main.pluginConfig.checkWarp(warpName)) {
            main.chat.sendMessage(player, main.pluginConfig.warpInvalidMessage(warpName));
            return;
        }
        player.teleport(main.pluginConfig.warpLocation(warpName));
        main.chat.sendMessage(player, main.pluginConfig.warpTeleportMessageAfterTeleport(warpName));
    }

}
