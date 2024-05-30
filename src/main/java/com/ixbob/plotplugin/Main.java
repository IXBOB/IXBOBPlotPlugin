package com.ixbob.plotplugin;

import com.ixbob.plotplugin.command.CommandMenu;
import com.ixbob.plotplugin.command.CommandPlot;
import com.ixbob.plotplugin.command.CommandSpawn;
import com.ixbob.plotplugin.event.*;
import com.ixbob.plotplugin.handler.config.LangLoader;
import com.ixbob.plotplugin.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin {
    public static Plugin plugin;
    public static Location spawnLocation;
    public static FileConfiguration config;
    public static List<Integer> bestPlotsList;
    public static final PlayerGUIManager GUIManager = new PlayerGUIManager();
    @Override
    public void onEnable() {
        plugin = this;
        MongoDB mongoDB = new MongoDB();
        mongoDB.connect("127.0.0.1", 27017, this);

        LangLoader.init(this);

        if (!Utils.isSysDataExist()) {
            Utils.firstInitDB();
        }

        spawnLocation  = new Location(Bukkit.getWorlds().get(0), 0 ,52, 0);

        //!! 覆盖保存config.yml
        saveResource("config.yml", true);

        config = getConfig();
        bestPlotsList = config.getIntegerList("menu.best_plots");

        this.getCommand("plot").setExecutor(new CommandPlot());
        this.getCommand("spawn").setExecutor(new CommandSpawn());
        this.getCommand("menu").setExecutor(new CommandMenu());

        Listener onPlayerBreakBlockListener = new OnPlayerBreakBlockEvent();
        getServer().getPluginManager().registerEvents(onPlayerBreakBlockListener, this);

        Listener onPlayerPlaceBlockListener = new OnPlayerPlaceBlockEvent();
        getServer().getPluginManager().registerEvents(onPlayerPlaceBlockListener, this);

        Listener onPlayerJoinListener = new OnPlayerJoinEvent();
        getServer().getPluginManager().registerEvents(onPlayerJoinListener, this);

        Listener onPlayerInteractListener = new OnPlayerInteractEvent();
        getServer().getPluginManager().registerEvents(onPlayerInteractListener, this);

        Listener onItemDropListener = new OnItemDropEvent();
        getServer().getPluginManager().registerEvents(onItemDropListener, this);

        Listener onEntitySpawnListener = new OnEntitySpawnEvent();
        getServer().getPluginManager().registerEvents(onEntitySpawnListener, this);

        Listener onInportalListener = new PlayerPortalEvent();
        getServer().getPluginManager().registerEvents(onInportalListener, this);

        Listener onRedstoneModifyListener = new OnRedstoneModifyEvent();
        getServer().getPluginManager().registerEvents(onRedstoneModifyListener, this);

        Listener pistonListener = new PistonEvent();
        getServer().getPluginManager().registerEvents(pistonListener, this);

        Listener blockSpreadListener = new BlockSpreadEvent();
        getServer().getPluginManager().registerEvents(blockSpreadListener, this);

        Listener playerCloseGUIListener = new OnPlayerCloseGUIListener();
        getServer().getPluginManager().registerEvents(playerCloseGUIListener, this);

        Listener onPlayerClickGUIListener = new OnPlayerClickGUIListener();
        getServer().getPluginManager().registerEvents(onPlayerClickGUIListener, this);

        Listener onEntityChangeBlockListener = new OnEntityChangeBlockEvent();
        getServer().getPluginManager().registerEvents(onEntityChangeBlockListener, this);

        Listener onEntityShootBowListener = new OnEntityShootBowEvent();
        getServer().getPluginManager().registerEvents(onEntityShootBowListener, this);

    }

    public static PlayerGUIManager getGUIManager() {
        return GUIManager;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

}
