package com.ixbob.plotplugin.command;

import com.ixbob.plotplugin.Main;
import com.ixbob.plotplugin.PlayerGUIManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMenu implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerGUIManager GUIManager = Main.getGUIManager();
            GUIManager.openMenuGUI(player);
        }
        return true;
    }
}
