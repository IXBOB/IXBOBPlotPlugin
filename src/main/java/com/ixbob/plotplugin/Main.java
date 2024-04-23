package com.ixbob.plotplugin;

import com.ixbob.plotplugin.command.CommandPlot;
import com.ixbob.plotplugin.handler.config.LangLoader;
import com.ixbob.plotplugin.util.Utils;
import com.mongodb.Mongo;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        MongoDB mongoDB = new MongoDB();
        mongoDB.connect("127.0.0.1", 27017, this);

        LangLoader.init(this);

        if (!Utils.isSysDataExist()) {
            Utils.firstInitDB();
        }

        this.getCommand("plot").setExecutor(new CommandPlot());
    }
}
