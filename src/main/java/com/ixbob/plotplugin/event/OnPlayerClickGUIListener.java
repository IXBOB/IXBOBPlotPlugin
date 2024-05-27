package com.ixbob.plotplugin.event;

import com.ixbob.plotplugin.Main;
import com.ixbob.plotplugin.gui.AbstractGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class OnPlayerClickGUIListener implements Listener {
    @EventHandler
    public void onPlayerClickGUI(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        AbstractGUI openingGUI = Main.getGUIManager().getOpeningGUI(player);
        //处理自定义物品栏
        if (openingGUI != null) {
            int index = event.getSlot();
            ClickType clickType = event.getClick();
            if (!openingGUI.isMoveable(index, clickType)) {
                event.setCancelled(true);
            }
            openingGUI.onClick(index, clickType);
        }
    }
}
