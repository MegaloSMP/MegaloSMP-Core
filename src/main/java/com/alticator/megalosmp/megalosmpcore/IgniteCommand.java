package com.alticator.megalosmp.megalosmpcore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class IgniteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length != 1) {
                sender.sendMessage(ChatColor.RED + "Only one argument allowed.");
                return false;
            }
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Cannot find " + args[0] + ". Is the player online and is the name written correctly?");
                return true;
            }

            if (Util.countPlayerMaterial(player, Material.GOLD_INGOT) >= 32) {
                Util.take(player, Material.GOLD_INGOT, 32);
                target.setFireTicks(500);
                player.sendMessage(ChatColor.GOLD + "Set player " + target.toString() + " on fire for 32 Gold!");
                target.sendMessage(ChatColor.GOLD + "You have been set on fire with the /ignite command!");
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "Not enough gold. (You have: " + Util.countPlayerMaterial(player, Material.GOLD_INGOT) + ", needed: 32)");
            }
        } else {
            sender.sendMessage("You can only use this command as a player.");
            return true;
        }
        return false;
    }
}
