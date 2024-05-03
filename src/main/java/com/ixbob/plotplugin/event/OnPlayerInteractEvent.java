package com.ixbob.plotplugin.event;

import com.ixbob.plotplugin.Main;
import com.ixbob.plotplugin.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class OnPlayerInteractEvent implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        EquipmentSlot hand = event.getHand();
        Action action = event.getAction();
        Player player = event.getPlayer();
        if (item != null) {
            Material itemType = item.getType();
            if (itemType == Material.MONSTER_EGG
                    || itemType == Material.ARMOR_STAND
                    || itemType == Material.TNT
                    || itemType == Material.EXPLOSIVE_MINECART
                    || itemType == Material.COMMAND_MINECART
                    || itemType == Material.HOPPER_MINECART
                    || itemType == Material.STORAGE_MINECART
                    || itemType == Material.END_CRYSTAL
                    || itemType == Material.POWERED_MINECART
                    || itemType == Material.MINECART
                    || itemType == Material.EGG
                    || itemType == Material.SNOW_BALL) {
                event.setCancelled(true);
            }
        }
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock != null) {
            Material blockType = clickedBlock.getType();
            if (action == Action.RIGHT_CLICK_BLOCK
                    &&(blockType == Material.STONE_BUTTON
                    || blockType == Material.WOOD_BUTTON
                    || blockType == Material.LEVER)) {
                event.setCancelled(true);
            }
            if (!Utils.isBlockInSelfPlot(clickedBlock, player)) {
                Block targetBlock = player.getTargetBlock(null, 6);
                if (targetBlock.getType() == Material.FIRE) {
                    System.out.println(12);
                    Location loc = new Location(targetBlock.getWorld(), targetBlock.getX(), targetBlock.getY(), targetBlock.getZ());
                    Bukkit.getServer().getScheduler().runTask(Main.plugin, () -> targetBlock.getWorld().getBlockAt(loc).setType(Material.FIRE));
                }
            }
        }
    }
}
