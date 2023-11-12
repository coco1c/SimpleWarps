package com.coco;

import com.coco.Animation.TeleportAnimation;
import com.coco.Events.WarpmenuManager;
import com.coco.GUI.WarpMenu;
import com.coco.Util.Chat;
import com.coco.Util.PluginConfig;
import com.coco.Util.PluginUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import com.coco.Commands.*;

public final class WarpMain extends JavaPlugin {
    WarpMain instance;
    FileConfiguration config;
    private SetWarp setwarp;
    private ReloadCommand reloadCommand;

    private WarpAll warpall;
    private WarpTeleport warp;
    private OpenWarpMenu openWarpMenu;
    private WarpmenuManager warpmenuManager;
    private DelWarp delWarp;
    public Chat chat;
    public WarpMenu warpMenu;
    public PluginUtil pluginUtil;
    public PluginConfig pluginConfig;
    public TeleportAnimation animation;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Starting Warp Plugin");
        getLogger().info("Created by Coco");
        getLogger().info("Version: " + getDescription().getVersion());
        getLogger().info("Github: https://github.com/coco1c/SimpleWarps/");

        instance = this;
        saveDefaultConfig();
        config = getConfig();
        getLogger().info("Loaded Config");
        reloadCommand = new ReloadCommand(this);
        pluginConfig = new PluginConfig(this);
        setwarp = new SetWarp(this);
        warp = new WarpTeleport(this);
        chat = new Chat();
        warpMenu = new WarpMenu(this);
        openWarpMenu = new OpenWarpMenu(this);
        pluginUtil = new PluginUtil(this);
        warpmenuManager = new WarpmenuManager(this);
        warpall = new WarpAll(this);
        delWarp = new DelWarp(this);
        animation = new TeleportAnimation(this);
        getLogger().info("Starting loading commands and events");
        // Tab Completers
        getCommand("warp").setTabCompleter(warp);
        getCommand("warpall").setTabCompleter(warp);
        // Commands
        getCommand("warpreload").setExecutor(reloadCommand);
        getCommand("setwarp").setExecutor(setwarp);
        getCommand("warp").setExecutor(warp);
        getCommand("warps").setExecutor(openWarpMenu);
        getCommand("warpall").setExecutor(warpall);
        getCommand("delwarp").setExecutor(delWarp);
        // Events
        getServer().getPluginManager().registerEvents(warpmenuManager, this);
        getLogger().info("Loaded commands and events");
        getLogger().info("Warp Plugin has been enabled!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
