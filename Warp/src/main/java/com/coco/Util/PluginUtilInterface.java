package com.coco.Util;

import org.bukkit.entity.Player;

public interface PluginUtilInterface {
    void teleportPlayerToWarp(Player player, String warpName);
    void teleportPlayerToWarpForce(Player player, String warpName);
}
