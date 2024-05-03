package com.ixbob.plotplugin.event;

import com.ixbob.plotplugin.util.Utils;
import org.bukkit.block.Block;
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
            Block block = event.getBlock();
            if (!Utils.isBlockInSelfPlot(block, player)) {
                event.setCancelled(true);
            }
        }

    }
}
