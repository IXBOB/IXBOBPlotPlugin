package com.ixbob.plotplugin.event;

import com.ixbob.plotplugin.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class OnPlayerCloseGUIListener implements Listener {
    @EventHandler
    public void onPlayerCloseGUI(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Main.getGUIManager().onCloseGUI(player);
    }
}
