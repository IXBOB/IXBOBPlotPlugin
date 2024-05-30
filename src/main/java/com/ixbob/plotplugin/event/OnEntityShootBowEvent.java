package com.ixbob.plotplugin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class OnEntityShootBowEvent implements Listener {
    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        event.setCancelled(true);
    }
}
