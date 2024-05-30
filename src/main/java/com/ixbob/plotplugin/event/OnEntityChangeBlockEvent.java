package com.ixbob.plotplugin.event;

import com.ixbob.plotplugin.util.Utils;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class OnEntityChangeBlockEvent implements Listener {
    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            Block block = event.getBlock();
            if (!Utils.isBlockInSelfPlot(block, player)) {
                event.setCancelled(true);
            }
        }
    }

}
