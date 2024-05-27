package com.ixbob.plotplugin.gui;

import com.ixbob.plotplugin.Main;
import com.ixbob.plotplugin.enums.GUIGridTypeEnum;
import com.ixbob.plotplugin.handler.config.LangLoader;
import com.ixbob.plotplugin.util.Utils;
import com.mongodb.DBObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class GUIBestPlots extends AbstractGUI {
    private static final int size = 54;
    private Inventory inventory;
    private Player player;
    private boolean movingState = false;
    private final ArrayList<Integer> idList = new ArrayList<>();
    private final ArrayList<String> authorNameList = new ArrayList<>();
    private final ArrayList<Location> locationList = new ArrayList<>();

    public GUIBestPlots(Player player) {
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
            int listIndex = index - 10;
            player.teleport(locationList.get(listIndex));
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_HARP, 1, 1);
            player.sendMessage(String.format(LangLoader.get("bestplots_teleport_success_message"), idList.get(listIndex), authorNameList.get(listIndex)));
            player.closeInventory();
        }
    }

    public void initContent() {
        leftButton.clear();
        rightButton.clear();

        int size = Main.bestPlotsList.size();
        for (int index = 10; index < 10 + size; index++) {
            int id = Main.bestPlotsList.get(index - 10);
            DBObject data = Utils.getPlayerData(id);
            String authorName = (String) data.get("owner_name");
            Location location = new Location(Bukkit.getWorlds().get(0), (double) data.get("X_from"), 55, (double) data.get("Z_from"));
            idList.add(id);
            authorNameList.add(authorName);
            locationList.add(location);

            String itemName = String.format(LangLoader.get("menu_bestplots_default_button_name"), authorName);
            ArrayList<String> itemLoreList = new ArrayList<>(Arrays.asList(
                    LangLoader.get("menu_bestplots_default_button_lore1"),
                    LangLoader.get("menu_bestplots_default_button_lore2"),
                    LangLoader.get("menu_bestplots_default_button_lore3")));
            itemLoreList.replaceAll(s -> s.replace("%PlotPosX%", String.valueOf(location.getX()))
                    .replace("%PlotPosZ%", String.valueOf(location.getZ()))
                    .replace("%PlotId%", String.valueOf(id)));
            ItemStack clickItem = new ItemStack(Material.SKULL_ITEM, 1);
            ItemMeta itemMeta = clickItem.getItemMeta();
            itemMeta.setDisplayName(itemName);
            itemMeta.setLore(itemLoreList);
            clickItem.setItemMeta(itemMeta);

            inventory.setItem(index, clickItem);
            leftButton.add(index);
        }
    }


}
