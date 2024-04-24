package com.ixbob.plotplugin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class OnRedstoneModifyEvent implements Listener {
    @EventHandler
    public void onRedstoneModify(BlockRedstoneEvent event) {
        event.setNewCurrent(0);
    }
}
