package com.coco.Util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Chat {
    public void sendMessage(Player player, String message) {
        player.sendMessage(ChatColorUtil.translateAlternateColorCodesAndHexes('&', message));
    }
}
