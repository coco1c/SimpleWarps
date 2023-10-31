package com.coco;

import com.coco.Util.PluginConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import com.coco.Commands.*;

public final class Warp extends JavaPlugin {
    Warp instance;
    FileConfiguration config;
    public PluginConfig pluginConfig;
    private setwarp setwarpp;
    private warp warpp;
    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        config = getConfig();
        pluginConfig = new PluginConfig(this);
        setwarpp = new setwarp(this);
        warpp = new warp(this);
        getCommand("setwarp").setExecutor(setwarpp);
        getCommand("warp").setExecutor(warpp);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
