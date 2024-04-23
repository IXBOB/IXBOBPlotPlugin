package com.ixbob.plotplugin.command;

import com.ixbob.plotplugin.handler.config.LangLoader;
import com.ixbob.plotplugin.util.Utils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandPlot implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            UUID uuid = player.getUniqueId();
            if (!Utils.isDataExist(uuid)) {
                DBObject obj = Utils.getSysData();
                double nextX = (double) obj.get("nextX");
                double nextY = 52;
                double nextZ = (double) obj.get("nextZ");
                Utils.createPlayerData(uuid, nextX, nextZ);
                double tpX = nextX - 50;
                double tpZ = nextZ - 50;
                int genCycle = (int) obj.get("genCycle");
                int nextGenCountInCycle = (int) obj.get("nextGenCountInCycle");

                Utils.genNewPlot(nextX, nextY, nextZ);
                nextGenCountInCycle++;
                if (nextGenCountInCycle <= genCycle * 2) {
                    nextZ += 106;
                }
                else if (nextGenCountInCycle > genCycle * 2
                        && nextGenCountInCycle <= genCycle * 2 - 1 + genCycle * 2) {
                    nextX += 106;
                }
                else if (nextGenCountInCycle > genCycle * 2 - 1 + genCycle * 2
                        && nextGenCountInCycle <= genCycle * 2 - 1 + genCycle * 2 + genCycle * 2 - 1) {
                    nextZ -= 106;
                }
                else {
                    nextX -= 106;
                    if (nextX == nextZ) {
                        nextX -= 106;
                        nextZ -= 106;

                        genCycle += 1;
                        nextGenCountInCycle = 1;
                    }
                    if (nextX < nextZ) {
                        throw new RuntimeException();
                    }
                }
                DBObject object = new BasicDBObject("id", 0);
                object.put("nextX", nextX);
                object.put("nextZ", nextZ);
                object.put("genCycle", genCycle);
                object.put("nextGenCountInCycle", nextGenCountInCycle);
                Utils.updateDBSysObj(object);

                //player
                player.teleport(new Location(Bukkit.getWorlds().get(0), tpX, 52, tpZ));
                player.sendTitle(LangLoader.get("plot_welcome_back_title"),
                        String.format(LangLoader.get("plot_owner_subtitle"), player.getName()), 10, 60, 10);
            }

            DBObject playerData = Utils.getPlayerData(uuid);
            double x = (double) playerData.get("X_from") - 50;
            double z = (double) playerData.get("Z_from") - 50;
            player.teleport(new Location(player.getWorld(), x, 52, z));
            player.sendTitle(LangLoader.get("plot_welcome_back_title"),
                    String.format(String.format(LangLoader.get("plot_owner_subtitle"), player.getName())), 10, 60, 10);
        }
        return true;
    }
}
