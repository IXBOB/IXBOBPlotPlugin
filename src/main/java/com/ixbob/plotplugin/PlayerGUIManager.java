package com.ixbob.plotplugin;

import com.ixbob.plotplugin.gui.AbstractGUI;
import com.ixbob.plotplugin.gui.GUIBestPlots;
import com.ixbob.plotplugin.gui.GUIMenuMain;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerGUIManager {
    private final Map<Player, AbstractGUI> playerOpeningCustomGUI = new HashMap<>();

    public PlayerGUIManager() {}

    public AbstractGUI getOpeningGUI(Player player) {
        return playerOpeningCustomGUI.get(player);
    }

    public void openMenuGUI(Player player) {
        GUIMenuMain gui = new GUIMenuMain(player);
        addPlayerToHashMap(player, gui);
        gui.initFrame(player);
        gui.open();
        gui.initContent();
    }

    public void openBestPlotsGUI(Player player) {
        GUIBestPlots gui = new GUIBestPlots(player);
        addPlayerToHashMap(player, gui);
        gui.initFrame(player);
        gui.open();
        gui.initContent();
    }


    public void onCloseGUI(Player player) {
        removePlayerFromHashMap(player);
    }

    private void addPlayerToHashMap(Player player, AbstractGUI gui) {
        if (playerOpeningCustomGUI.containsKey(player)) {
            throw new IllegalArgumentException("Player " + player + " is already has opening custom gui");
        }
        playerOpeningCustomGUI.put(player, gui);
    }

    private void removePlayerFromHashMap(Player player) {
        playerOpeningCustomGUI.remove(player);
    }
}
