package com.coco.Events;

import com.coco.Util.ChatColorUtil;
import com.coco.WarpMain;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class WarpmenuManager implements Listener {
    private WarpMain main;
    public WarpmenuManager(WarpMain main) {
        this.main = main;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equalsIgnoreCase(ChatColorUtil.translateAlternateColorCodesAndHexes('&', main.pluginConfig.warpMenuTitle()))) {
            return;
        }
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta())
            return;
        String itemName = ChatColorUtil.translateAlternateColorCodesAndHexes('&', event.getCurrentItem().getType().toString());
        ConfigurationSection warpsSection = main.getConfig().getConfigurationSection("warp_menu.warps");

        if (warpsSection != null) {
            for (String warp : warpsSection.getKeys(false)) {
                ConfigurationSection warpData = warpsSection.getConfigurationSection(warp);

                if (warpData != null) {
                    if (warpData.getString("material").equalsIgnoreCase(itemName) && !warpData.getBoolean("ignored")) {
                        main.getLogger().info(warpData.getString("material"));
                        String warpName = warpData.getString("warp_name");
                        main.pluginUtil.teleportPlayerToWarp(player, warpName);
                        player.closeInventory();
                        return;
                    }
                }

            }
        }
    }
}
