package com.ixbob.plotplugin.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnPlayerBreakBlockEvent implements Listener {
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.getMetadata("registered").get(0).asBoolean()) {
            event.setCancelled(true);
        } else {
            double breakBlockX = event.getBlock().getLocation().getBlockX();
            double breakBlockZ = event.getBlock().getLocation().getBlockZ();
            double plotXFrom = player.getMetadata("X_from").get(0).asDouble();
            double plotZFrom = player.getMetadata("Z_from").get(0).asDouble();
            if (!(plotXFrom - 100 <= breakBlockX && breakBlockX <= plotXFrom - 1
                    && plotZFrom - 100 <= breakBlockZ && breakBlockZ <= plotZFrom - 1)) {
                event.setCancelled(true);
            }
        }

    }
}
