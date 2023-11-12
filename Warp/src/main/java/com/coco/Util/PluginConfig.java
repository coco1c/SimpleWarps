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
    private boolean warpInProgress = false;
    public PluginConfig(WarpMain main) {
        this.main = main;
        config = main.getConfig();
    }
    public boolean checkWarp(String name) {
        return config.contains("warps." + name);
    }
    public double getWarpX(String name) {
        return config.getDouble("warps." + name + ".x");
    }
    public double getWarpY(String name) {
        return config.getDouble("warps." + name + ".y");
    }
    public double getWarpZ(String name) {
        return config.getDouble("warps." + name + ".z");
    }
    public float getWarpPitch(String name) {
        return (float) config.getDouble("warps." + name + ".pitch");
    }
    public float getWarpYaw(String name) {
        return (float) config.getDouble("warps." + name + ".yaw");
    }
    public void setWarp(String name, double x, double y, double z, float pitch, float yaw ,String world) {
        config.set("warps." + name + ".x", x);
        config.set("warps." + name + ".y", y);
        config.set("warps." + name + ".z", z);
        config.set("warps." + name + ".pitch", pitch);
        config.set("warps." + name + ".yaw", yaw);
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
    public int teleportCooldown() {
        return config.getInt("warp_settings.cooldown");
    }
    public Location warpLocation(String name) {
        return new Location(main.getServer().getWorld(config.getString("warps." + name + ".world")), getWarpX(name), getWarpY(name), getWarpZ(name), getWarpYaw(name), getWarpPitch(name));
    }
    public void deleteWarp(String name) {
        config.set("warps." + name, null);
        main.saveConfig();
    }
    public void deleteAllWarps() {
        config.set("warps", null);
        main.saveConfig();
    }
    public String warpParticle() {
        return config.getString("warp_settings.particle");
    }
    public int warpParticleAmount() {
        return config.getInt("warp_settings.particle_amount");
    }
    public boolean warpParticleEnabled() {
        return config.getBoolean("warp_settings.particle_enabled");
    }
    public int warpParticleLocationY() {
        return config.getInt("warp_settings.particle_location_y");
    }
    public boolean warpSoundEnabled() {
        return config.getBoolean("warp_settings.sound_enabled");
    }
    public String warpSound() {
        return config.getString("warp_settings.sound");
    }
    public String warpExistsMessage(String name) {
        return config.getString("Messages.warpExists").replace("{warp}", name);
    }
    public String delWarpMessage(String name) {
        return config.getString("Messages.warp_deleted").replace("{warp}", name);
    }
    public String warpSetMessage(String name) {
        return config.getString("Messages.warp_set").replace("{warp}", name);
    }
    public String warpTeleportMessageAfterTeleport(String name) {
        return config.getString("Messages.warp_teleport_after_teleport").replace("{warp}", name);
    }
    public String warpUpdatedMessage(String name) {
        return config.getString("Messages.warp_updated").replace("{warp}", name);
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
    public String warpInProgressTryAgainMessage() {
        return config.getString("Messages.warp_in_progress_try_again");
    }
    public String delAllWarpsMessage() {
        return config.getString("Messages.warp_delete_all");
    }
    public String getWarpWorld(String name) {
        return config.getString("warps." + name + ".world");
    }
    public String getWarpLocation(String name, String xColor, String yColor, String zColor, String worldColor) {
        return xColor + getWarpX(name) + yColor + ", " + getWarpY(name) + zColor + ", " + getWarpZ(name) + worldColor + " (" + getWarpWorld(name) + ")";
    }
    public String warpWelcomeMessage() {
        return config.getString("Messages.warp_welcome_message");
    }
    public boolean isWarpWelcomeMessageEnabled() {
        return config.getBoolean("warp_settings.warp_welcome_message_enabled");
    }
    public boolean isWarpInProgress(){
        return warpInProgress;
    }
    public void setWarpInProgress(boolean value) {
        warpInProgress = value;
    }
    public void updateWarp(String warpName, Location location) {
        config.set("warps." + warpName + ".x", location.getX());
        config.set("warps." + warpName + ".y", location.getY());
        config.set("warps." + warpName + ".z", location.getZ());
        config.set("warps." + warpName + ".pitch", location.getPitch());
        config.set("warps." + warpName + ".yaw", location.getYaw());
        config.set("warps." + warpName + ".world", location.getWorld().getName());
        main.saveConfig();
    }
    public void updateWarp(String warpName, double x, double y, double z, float pitch, float yaw, String world) {
        config.set("warps." + warpName + ".x", x);
        config.set("warps." + warpName + ".y", y);
        config.set("warps." + warpName + ".z", z);
        config.set("warps." + warpName + ".pitch", pitch);
        config.set("warps." + warpName + ".yaw", yaw);
        config.set("warps." + warpName + ".world", world);
        main.saveConfig();
    }
    public void updateWarp(String warpName, double x, double y, double z, String world) {
        config.set("warps." + warpName + ".x", x);
        config.set("warps." + warpName + ".y", y);
        config.set("warps." + warpName + ".z", z);
        config.set("warps." + warpName + ".world", world);
        main.saveConfig();
    }
    public void reloadAndLoadConfig() {
        main.reloadConfig();
        config = main.getConfig();
    }
}
