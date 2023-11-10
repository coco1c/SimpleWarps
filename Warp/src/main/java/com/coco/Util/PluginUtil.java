package com.coco.Util;

import com.coco.WarpMain;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PluginUtil implements IPluginUtil {
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
        if (main.pluginConfig.isWarpInProgress()) {
            main.chat.sendMessage(player, main.pluginConfig.warpInProgressMessage(warpName));
            return;
        }
        main.chat.sendMessage(player, main.pluginConfig.warpInProgressMessage(warpName));
        Location playerLocation = player.getLocation();
        Timer timer = new Timer(main);
        main.pluginConfig.setWarpInProgress(true);

        timer.startTimer(main.pluginConfig.teleportCooldown(), () -> {
            player.teleport(main.pluginConfig.warpLocation(warpName));
            main.pluginConfig.setWarpInProgress(false);
            main.chat.sendMessage(player, main.pluginConfig.warpTeleportMessageAfterTeleport(warpName));
        }, () -> {
            main.animation.spawnParticles(player);
            main.animation.playSound(player);
            if (player.getLocation().getBlockX() != playerLocation.getBlockX() || player.getLocation().getBlockY() != playerLocation.getBlockY() || player.getLocation().getBlockZ() != playerLocation.getBlockZ()) {
                main.chat.sendMessage(player, main.pluginConfig.warpCancelledMessage());
                timer.cancelTimer();
                main.pluginConfig.setWarpInProgress(false);
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

    @Override
    public void sendConsoleMessage(String message) {
        main.getLogger().info(message);
    }


}
