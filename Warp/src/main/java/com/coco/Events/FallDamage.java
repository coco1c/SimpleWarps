package com.coco.Events;

import com.coco.WarpMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FallDamage implements Listener {
    WarpMain main;
    public FallDamage(WarpMain main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerFall(org.bukkit.event.entity.EntityDamageEvent event) {
        if (event.getCause() == org.bukkit.event.entity.EntityDamageEvent.DamageCause.FALL) {
            if (main.pluginConfig.isWarpInProgress()) {
                event.setCancelled(true);
            }
        }
    }
}
