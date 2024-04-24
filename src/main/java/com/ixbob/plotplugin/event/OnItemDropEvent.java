package com.ixbob.plotplugin.event;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OnItemDropEvent implements Listener {
    @EventHandler
    public void onItemDrop(EntitySpawnEvent event) {
        if (event.getEntity().getType() == EntityType.DROPPED_ITEM) {
            event.setCancelled(true);
        }
    }
}
