package com.ixbob.plotplugin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.world.PortalCreateEvent;

public class OnPortalCreateEvent implements Listener {
    @EventHandler
    public void onPortalCreate(PortalCreateEvent event) { //seem useless
        event.setCancelled(true);
    }
}
