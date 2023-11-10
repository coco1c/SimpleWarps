package com.coco;

import com.coco.Animation.TeleportAnimation;
import com.coco.Events.FallDamage;
import com.coco.Events.WarpmenuManager;
import com.coco.GUI.WarpMenu;
import com.coco.Util.Chat;
import com.coco.Util.PluginConfig;
import com.coco.Util.PluginUtil;
import com.coco.Util.Timer;
import org.bukkit.Bukkit;
import org.bukkit.command.TabCompleter;
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
    private FallDamage fallDamage;
    public Chat chat;
    public WarpMenu warpMenu;
    public PluginUtil pluginUtil;
    public PluginConfig pluginConfig;
    public TeleportAnimation animation;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        saveDefaultConfig();
        config = getConfig();
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
        fallDamage = new FallDamage(this);
        animation = new TeleportAnimation(this);

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
        getServer().getPluginManager().registerEvents(fallDamage, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
