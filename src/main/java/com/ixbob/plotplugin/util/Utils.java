package com.ixbob.plotplugin.util;

import com.ixbob.plotplugin.Main;
import com.ixbob.plotplugin.MongoDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.UUID;

public class Utils {
    public static final MongoDB dbPlot = new MongoDB("Plot");
    public static boolean isDBEmpty () {
        return dbPlot.getCollectionSize() == 0;
    }
    public static void firstInitDB() {
        DBObject obj = new BasicDBObject("id", 0);
        obj.put("nextX", (double)-3);
        obj.put("nextZ", (double)-3);

        obj.put("genCycle", 1);
        obj.put("nextGenCountInCycle", 1);
        dbPlot.insert(obj);
    }

    public static void playerJoinInitData(Player player) {
        UUID uuid = player.getUniqueId();
        player.setMetadata("registered", new FixedMetadataValue(Main.plugin, false));
        if (dbPlot.isFindByUUIDExist(uuid)) {
            playerInitEntityData(player);
        }
    }

    public static void playerInitEntityData(Player player) {
        UUID uuid = player.getUniqueId();
        DBObject obj = dbPlot.findByOwnerUUID(uuid);
        player.setMetadata("X_from", new FixedMetadataValue(Main.plugin, obj.get("X_from")));
        player.setMetadata("Z_from", new FixedMetadataValue(Main.plugin, obj.get("Z_from")));
        player.setMetadata("registered", new FixedMetadataValue(Main.plugin, true));
    }

    public static boolean isDataExist(UUID uuid) {
        return dbPlot.isFindByUUIDExist(uuid);
    }

    public static boolean isSysDataExist() {
        return dbPlot.isFindByIDExist(0);
    }

    public static void createPlayerData(UUID uuid, double nextX, double nextZ) {
        String uuidString = uuid.toString();
        DBObject obj = new BasicDBObject("id", dbPlot.getCollectionSize());
        obj.put("owner_uuid", uuidString);
        obj.put("owner_name", Bukkit.getPlayer(uuid).getName());
        obj.put("X_from", nextX);
        obj.put("X_to", (nextX - 100));
        obj.put("Z_from", nextZ);
        obj.put("Z_to", (nextZ - 100));
        dbPlot.insert(obj);
    }

    public static DBObject getPlayerData(UUID uuid) {
        return dbPlot.findByOwnerUUID(uuid);
    }

    public static DBObject getSysData() {
        return dbPlot.findByID(0);
    }

    public static void genNewPlot(double x, double y, double z) {
        Location loc = new Location(Bukkit.getWorlds().get(0), x, y, z);
        genPlotFunc(loc);
    }

    public static void genPlotFunc(Location location) {
        for (int i = 0; i <= 100; i++) {
            location.add(-1 ,0 ,0).getBlock().setType(Material.STONE_SLAB2);
        }
        for (int i = 0; i <= 100; i++) {
            location.add(0 ,0 ,-1).getBlock().setType(Material.STONE_SLAB2);
        }
        for (int i = 0; i <= 100; i++) {
            location.add(1 ,0 ,0).getBlock().setType(Material.STONE_SLAB2);
        }
        for (int i = 0; i <= 100; i++) {
            location.add(0 ,0 ,1).getBlock().setType(Material.STONE_SLAB2);
        }
    }

    public static void updateDBSysObj(DBObject object) {
        dbPlot.updateSys(object);
    }

    public static boolean isBlockInSelfPlot(Block block, Player player) {
        double breakBlockX = block.getLocation().getBlockX();
        double breakBlockZ = block.getLocation().getBlockZ();
        double plotXFrom = player.getMetadata("X_from").get(0).asDouble();
        double plotZFrom = player.getMetadata("Z_from").get(0).asDouble();
        return (plotXFrom - 100 <= breakBlockX && breakBlockX <= plotXFrom - 1
                && plotZFrom - 100 <= breakBlockZ && breakBlockZ <= plotZFrom - 1);
    }
}
