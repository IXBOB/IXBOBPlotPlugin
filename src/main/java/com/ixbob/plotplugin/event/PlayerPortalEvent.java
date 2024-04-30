package com.ixbob.plotplugin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerPortalEvent implements Listener {
    @EventHandler
    public void onInPortal(org.bukkit.event.player.PlayerPortalEvent event) { //seem useless
        event.setCancelled(true);
    }
}
