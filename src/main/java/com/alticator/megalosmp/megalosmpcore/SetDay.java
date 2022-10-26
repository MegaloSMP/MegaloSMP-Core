package com.alticator.megalosmp.megalosmpcore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetDay implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Util.countPlayerMaterial(player, Material.GOLD_INGOT) >= 32) {
                Util.take(player, Material.GOLD_INGOT, 32);
                player.getWorld().setTime(1000);
                player.sendMessage(ChatColor.GREEN + "Set time to day for 32 Gold!");
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "Not enough gold. (Needed: 32, You have: " + Util.countPlayerMaterial(player, Material.GOLD_INGOT));
            }
        } else {
            sender.sendMessage("This command cannot be run from the console. Use '/time set day' instead.");
        }
        return false;
    }
}
