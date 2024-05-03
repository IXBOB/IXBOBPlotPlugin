package com.ixbob.plotplugin;

import com.ixbob.plotplugin.command.CommandPlot;
import com.ixbob.plotplugin.event.*;
import com.ixbob.plotplugin.handler.config.LangLoader;
import com.ixbob.plotplugin.util.Utils;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Plugin plugin;
    @Override
    public void onEnable() {
        plugin = this;
        MongoDB mongoDB = new MongoDB();
        mongoDB.connect("127.0.0.1", 27017, this);

        LangLoader.init(this);

        if (!Utils.isSysDataExist()) {
            Utils.firstInitDB();
        }

        this.getCommand("plot").setExecutor(new CommandPlot());

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

    }
}
