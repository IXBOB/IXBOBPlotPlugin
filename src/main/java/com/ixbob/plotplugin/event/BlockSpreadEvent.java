package com.ixbob.plotplugin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;

public class BlockSpreadEvent implements Listener {
    @EventHandler
    public void onBlockSpread(BlockFromToEvent event) {
        if (event.getBlock().isLiquid()) {
            event.setCancelled(true);
        }
    }
}
