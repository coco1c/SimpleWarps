package com.coco;

import com.coco.Events.WarpmenuManager;
import com.coco.GUI.WarpMenu;
import com.coco.Util.Chat;
import com.coco.Util.PluginConfig;
import com.coco.Util.PluginUtil;
import com.coco.Util.Timer;
import com.coco.lib.CocoLib;
import org.bukkit.Bukkit;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import com.coco.Commands.*;

public final class WarpMain extends JavaPlugin {
    WarpMain instance;
    JavaPlugin plugin = this;
    FileConfiguration config;
    private SetWarp setwarp;
    private WarpAll warpall;
    private WarpTeleport warp;
    private CocoLib cocoLib;
    private OpenWarpMenu openWarpMenu;
    private WarpmenuManager warpmenuManager;
    public Chat chat;
    public WarpMenu warpMenu;
    public PluginUtil pluginUtil;
    public PluginConfig pluginConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        config = getConfig();
        pluginConfig = new PluginConfig(this);
        setwarp = new SetWarp(this);
        warp = new WarpTeleport(this);
        chat = new Chat();
        warpMenu = new WarpMenu(this);
        openWarpMenu = new OpenWarpMenu(this);
        pluginUtil = new PluginUtil(this);
        warpmenuManager = new WarpmenuManager(this);
        warpall = new WarpAll(this);
        // lib
        // Tab Completers
        getCommand("warp").setTabCompleter(warp);
        getCommand("warpall").setTabCompleter(warp);
        // Commands
        getCommand("setwarp").setExecutor(setwarp);
        getCommand("warp").setExecutor(warp);
        getCommand("warps").setExecutor(openWarpMenu);
        getCommand("warpall").setExecutor(warpall);
        // Events
        getServer().getPluginManager().registerEvents(warpmenuManager, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
