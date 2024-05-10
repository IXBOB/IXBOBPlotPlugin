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
            if (itemType == Material.MONSTER_EGG
                    || itemType == Material.FIREWORK
                    || itemType == Material.PAINTING
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
                    || itemType == Material.SNOW_BALL
                    || itemType == Material.EYE_OF_ENDER
                    || itemType == Material.ENDER_PEARL
                    || itemType == Material.LINGERING_POTION
                    || itemType == Material.SPLASH_POTION
                    || itemType == Material.BOAT
                    || itemType == Material.BOAT_ACACIA
                    || itemType == Material.BOAT_BIRCH
                    || itemType == Material.BOAT_SPRUCE
                    || itemType == Material.BOAT_DARK_OAK
                    || itemType == Material.BOAT_JUNGLE) {
                event.setCancelled(true);
            }
        }
        if (clickedBlock != null) {
            Material blockType = clickedBlock.getType();
            if (action == Action.RIGHT_CLICK_BLOCK
                    &&(blockType == Material.STONE_BUTTON
                    || blockType == Material.WOOD_BUTTON
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
