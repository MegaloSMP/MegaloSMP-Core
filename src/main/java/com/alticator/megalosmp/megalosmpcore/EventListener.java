package com.alticator.megalosmp.megalosmpcore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        Material blockType = block.getType();

        if (blockType == Material.CYAN_TERRACOTTA) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 10));
        } else if (blockType == Material.CYAN_CONCRETE) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10, 2));
        }
    }
}
