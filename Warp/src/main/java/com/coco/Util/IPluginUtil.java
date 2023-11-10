package com.coco.Util;

import com.coco.WarpMain;
import org.bukkit.entity.Player;

public interface IPluginUtil {
    void teleportPlayerToWarp(Player player, String warpName);
    void teleportPlayerToWarpForce(Player player, String warpName);
    void sendConsoleMessage(String message);
}
