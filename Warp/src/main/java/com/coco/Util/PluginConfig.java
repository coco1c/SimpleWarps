package com.coco.Util;

import com.coco.WarpMain;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.List;
import java.util.Set;


public class PluginConfig {
    FileConfiguration config;
    WarpMain main;
    public PluginConfig(WarpMain main) {
        this.main = main;
        config = main.getConfig();
    }
    public boolean checkWarp(String name) {
        return config.contains("warps." + name);
    }
    public int getWarpX(String name) {
        return config.getInt("warps." + name + ".x");
    }
    public int getWarpY(String name) {
        return config.getInt("warps." + name + ".y");
    }
    public int getWarpZ(String name) {
        return config.getInt("warps." + name + ".z");
    }
    public void setWarp(String name, int x, int y, int z, String world) {
        config.set("warps." + name + ".x", x);
        config.set("warps." + name + ".y", y);
        config.set("warps." + name + ".z", z);
        config.set("warps." + name + ".world", world);
        main.saveConfig();
    }
    public Set<String> getAllWarpNames() {
        return config.getConfigurationSection("warps").getKeys(false);
    }
    public List<String> getAllWarps() {
        return config.getStringList("warps");
    }
    public boolean isWarpMenuEnabled() {
        return config.getBoolean("warp_menu.enable");
    }
    public String warpMenuTitle() {
        return config.getString("warp_menu.title");
    }
    public int warpMenuSize() {
        return config.getInt("warp_menu.size");
    }
    public List<ItemStack> items() {
        return (List<ItemStack>) config.getList("warp_menu.warps");
    }
    public int teleportCooldown() {
        return config.getInt("warp_settings.cooldown");
    }
    public Location warpLocation(String name) {
        return new Location(main.getServer().getWorld(config.getString("warps." + name + ".world")), getWarpX(name), getWarpY(name), getWarpZ(name));
    }
    public String warpExistsMessage(String name) {
        return config.getString("Messages.warpExists").replace("{warp}", name);
    }
    public String warpSetMessage(String name) {
        return config.getString("Messages.warp_set").replace("{warp}", name);
    }
    public String warpTeleportMessageAfterTeleport(String name) {
        return config.getString("Messages.warp_teleport_after_teleport").replace("{warp}", name);
    }
    public String warpInProgressMessage(String name) {
        return config.getString("Messages.warp_in_progress").replace("{warp}", name).replace("{cooldown}", String.valueOf(teleportCooldown()));
    }
    public String warpInvalidMessage(String name) {
        return config.getString("Messages.warp_invalid").replace("{warp}", name);
    }
    public String warpCancelledMessage() {
        return config.getString("Messages.warp_cancelled");
    }
}
