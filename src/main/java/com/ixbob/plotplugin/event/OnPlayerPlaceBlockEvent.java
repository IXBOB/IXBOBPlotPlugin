package com.ixbob.plotplugin.event;

import com.ixbob.plotplugin.handler.config.LangLoader;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnPlayerPlaceBlockEvent implements Listener {
    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (block.getType() == Material.DISPENSER) {
            event.setCancelled(true);
        }

        if (!player.getMetadata("registered").get(0).asBoolean()) {
            event.setCancelled(true);
        } else {
            double placeBlockX = event.getBlock().getLocation().getBlockX();
            double placeBlockZ = event.getBlock().getLocation().getBlockZ();
            double plotXFrom = player.getMetadata("X_from").get(0).asDouble();
            double plotZFrom = player.getMetadata("Z_from").get(0).asDouble();
            if (!(plotXFrom - 100 <= placeBlockX && placeBlockX <= plotXFrom - 1
                    && plotZFrom - 100 <= placeBlockZ && placeBlockZ <= plotZFrom - 1)) {
                event.setCancelled(true);
            }
        }
    }
}
