package com.ixbob.plotplugin.enums;

import com.ixbob.plotplugin.handler.config.LangLoader;
import com.ixbob.plotplugin.util.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum GUIItemEnum {
    MAIN_OPEN_BEST_PLOTS(Material.DIAMOND, 1, Utils.getInventoryIndex(2, 2),
            LangLoader.get("menu_main_button_open_best_plots_name"), new ArrayList<>(Arrays.asList(
                    LangLoader.get("menu_main_button_open_best_plots_lore1"),
                    LangLoader.get("menu_main_button_open_best_plots_lore2"),
                    LangLoader.get("menu_main_button_open_best_plots_lore3"))));

    private final Material material;
    private final int amount;
    private final int index;
    private final String displayName;
    private final List<String> loreList;

    GUIItemEnum(Material material, int amount, int index, String displayName, List<String> loreList) {
        this.material = material;
        this.amount = amount;
        this.index = index;
        this.displayName = displayName;
        this.loreList = loreList;
    }

    public ItemStack getNamedItem() {
        ItemStack itemStack = this.getItemStack();
        ItemMeta itemMeta = itemStack.getItemMeta();
        return defaultNameMethod(itemStack, itemMeta);
    }

    private ItemStack defaultNameMethod(ItemStack itemStack, ItemMeta itemMeta) {
        ArrayList<String> copiedLoreList = new ArrayList<>(loreList);
        itemMeta.setLore(copiedLoreList);
        itemMeta.setDisplayName(this.getDisplayName());
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack getItemStack() {
        return new ItemStack(material);
    }

    public Material getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }

    public int getIndex() {
        return index;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getLoreList() {
        return loreList;
    }
}
