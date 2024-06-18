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
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class OnPlayerInteractEvent implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            Location entityLoc = event.getEntity().getLocation();
            double x = entityLoc.getX();
            double z = entityLoc.getZ();
            if (!Utils.isPosXZInSelfPlot(x, z, player)) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlayerDropHangingEntity(HangingBreakByEntityEvent event) {
        if (event.getRemover() instanceof Player) {
            Player player = (Player) event.getRemover();
            Location entityLoc = event.getEntity().getLocation();
            double x = entityLoc.getX();
            double z = entityLoc.getZ();
            if (!Utils.isPosXZInSelfPlot(x, z, player)) {
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Location entityLoc = event.getRightClicked().getLocation();
        double x = entityLoc.getX();
        double z = entityLoc.getZ();
        if (!Utils.isPosXZInSelfPlot(x, z, player)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        Action action = event.getAction();
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (item != null) {
            Material itemType = item.getType();
            if (itemType == Material.LEGACY_MOB_SPAWNER
                    || itemType == Material.FIREWORK_ROCKET
                    || itemType == Material.PAINTING
                    || itemType == Material.ARMOR_STAND
                    || itemType == Material.TNT
                    || itemType == Material.TNT_MINECART
                    || itemType == Material.COMMAND_BLOCK_MINECART
                    || itemType == Material.HOPPER_MINECART
                    || itemType == Material.CHEST_MINECART
                    || itemType == Material.END_CRYSTAL
                    || itemType == Material.FURNACE_MINECART
                    || itemType == Material.MINECART
                    || itemType == Material.EGG
                    || itemType == Material.SNOWBALL
                    || itemType == Material.ENDER_EYE
                    || itemType == Material.ENDER_PEARL
                    || itemType == Material.LINGERING_POTION
                    || itemType == Material.SPLASH_POTION
                    || itemType == Material.ACACIA_BOAT
                    || itemType == Material.BIRCH_BOAT
                    || itemType == Material.DARK_OAK_BOAT
                    || itemType == Material.JUNGLE_BOAT
                    || itemType == Material.SPRUCE_BOAT
                    || itemType == Material.OAK_BOAT) {
                event.setCancelled(true);
            }
        }
        if (clickedBlock != null) {
            Material blockType = clickedBlock.getType();
            if (action == Action.RIGHT_CLICK_BLOCK
                    &&(blockType == Material.STONE_BUTTON
                    || blockType == Material.BIRCH_BUTTON
                    || blockType == Material.DARK_OAK_BUTTON
                    || blockType == Material.OAK_BUTTON
                    || blockType == Material.ACACIA_BUTTON
                    || blockType == Material.JUNGLE_BUTTON
                    || blockType == Material.SPRUCE_BUTTON
                    || blockType == Material.LEVER
                    || blockType == Material.DRAGON_EGG)) {
                event.setCancelled(true);
            }
            if (!Utils.isBlockInSelfPlot(clickedBlock, player)) {
                event.setCancelled(true);
            }
        }
    }
}
