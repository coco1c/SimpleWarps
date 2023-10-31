package com.coco.Util;

import com.coco.Warp;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;


public class PluginConfig {
    FileConfiguration config;
    Warp main;
    public PluginConfig(Warp main) {
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
    public Location warpLocation(String name) {
        return new Location(main.getServer().getWorld(config.getString("warps." + name + ".world")), getWarpX(name), getWarpY(name), getWarpZ(name));
    }
}
