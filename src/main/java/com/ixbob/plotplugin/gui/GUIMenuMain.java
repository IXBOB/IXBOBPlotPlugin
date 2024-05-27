package com.ixbob.plotplugin.gui;

import com.ixbob.plotplugin.Main;
import com.ixbob.plotplugin.enums.GUIGridTypeEnum;
import com.ixbob.plotplugin.enums.GUIItemEnum;
import com.ixbob.plotplugin.handler.config.LangLoader;
import com.ixbob.plotplugin.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class GUIMenuMain extends AbstractGUI {
    private static final int size = 54;
    private Inventory inventory;
    private Player player;
    private boolean movingState = false;

    public GUIMenuMain(Player player) {
        super(player, size, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    public void initFrame(Player player) {
        this.inventory = Bukkit.createInventory(player, size, LangLoader.get("menu_main_title"));
        this.player = player;
    }

    public void open() {
        player.openInventory(inventory);
    }

    @Override
    public void onClick(int index, ClickType clickType) {
        GUIGridTypeEnum type = getGridType(index, clickType);
        if (type == GUIGridTypeEnum.LEFT_BUTTON) {
            //打开最佳地皮菜单
            if (index == Utils.getInventoryIndex(2,2)) {
                player.closeInventory();
                Main.getGUIManager().openBestPlotsGUI(player);
            }
        }
    }

    public void initContent() {
        leftButton.clear();
        rightButton.clear();

        inventory.setItem(GUIItemEnum.MAIN_OPEN_BEST_PLOTS.getIndex(), GUIItemEnum.MAIN_OPEN_BEST_PLOTS.getNamedItem());
        leftButton.add(GUIItemEnum.MAIN_OPEN_BEST_PLOTS.getIndex());
    }
}
