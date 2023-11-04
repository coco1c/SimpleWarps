package com.coco.GUI;

import com.coco.Util.ChatColorUtil;
import com.coco.WarpMain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class WarpMenu {
    private WarpMain main;

    public WarpMenu(WarpMain main) {
        this.main = main;
    }

    public void openWarpMenu(Player player) {
        if (!main.pluginConfig.isWarpMenuEnabled()) {
            main.chat.sendMessage(player, "&cWarp Menu is disabled!");
            return;
        }

        Inventory inv = Bukkit.createInventory(null, main.pluginConfig.warpMenuSize(), ChatColorUtil.translateAlternateColorCodesAndHexes('&', main.pluginConfig.warpMenuTitle()));
        ConfigurationSection warpsSection = main.getConfig().getConfigurationSection("warp_menu.warps");

        if (warpsSection != null) {
            for (String warpName : warpsSection.getKeys(false)) {
                ConfigurationSection warpData = warpsSection.getConfigurationSection(warpName);

                if (warpData != null) {
                    Material material = Material.valueOf(warpData.getString("material"));
                    ItemStack item = new ItemStack(material);

                    ItemMeta itemMeta = item.getItemMeta();
                    itemMeta.setDisplayName(ChatColorUtil.translateAlternateColorCodesAndHexes('&', warpData.getString("name") ).replace("{warp}", warpData.getString("warp_name")));
                    List<String> lore = warpData.getStringList("lore");
                    List<String> translatedLore = new ArrayList<>();
                    for (String line : lore) {
                        String translatedLine = ChatColorUtil.translateAlternateColorCodesAndHexes('&', line).replace("{warp}", warpData.getString("warp_name"));
                        translatedLore.add(translatedLine);
                    }
                    itemMeta.setLore(translatedLore);
                    item.setItemMeta(itemMeta);
                    inv.setItem(warpData.getInt("slot"), item);
                }
            }
        }

        player.openInventory(inv);
    }
}
