package com.alticator.megalosmp.megalosmpcore;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class Pack implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player && args.length == 1) {
            Player player = (Player) sender;
            ItemStack item;
            int price;

            int dyePrice = 8;

            switch (args[0].toLowerCase().replace("_", "")) {
                case "glow":
                    item = new ItemStack(Material.GLOW_INK_SAC, 32);
                    price = 16;
                    break;
                case "whitedye":
                    item = new ItemStack(Material.WHITE_DYE, 32);
                    price = 16;
                    break;
                case "greendye":
                    item = new ItemStack(Material.GREEN_DYE, 32);
                    price = dyePrice;
                    break;
                case "reddye":
                    item = new ItemStack(Material.RED_DYE, 32);
                    price = dyePrice;
                    break;
                case "cyandye":
                    item = new ItemStack(Material.CYAN_DYE, 32);
                    price = dyePrice;
                    break;
                case "propick":
                    item = new ItemStack(Material.IRON_PICKAXE, 1);
                    item.addEnchantment(Enchantment.DIG_SPEED, 5);
                    item.addEnchantment(Enchantment.DURABILITY, 3);;
                    price = 64;
                    break;
                case "ultimatepick":
                    item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
                    item.addEnchantment(Enchantment.DIG_SPEED, 5);
                    item.addEnchantment(Enchantment.DURABILITY, 3);
                    price = 128;
                case "destroyerpick":
                    item = new ItemStack(Material.WOODEN_PICKAXE, 1);
                    item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
                    item.addUnsafeEnchantment(Enchantment.LUCK, 10);
                    price = 128;
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "Invalid kit name.");
                    return false;
            }

            if (Util.countPlayerMaterial(player, Material.GOLD_INGOT) >= price) {
                Util.take(player, Material.GOLD_INGOT, price);
                player.getInventory().addItem(item);
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "Not enough gold. (You have: " + Util.countPlayerMaterial(player, Material.GOLD_INGOT) + ", needed: " + price + ")");
            }
        } else {
            sender.sendMessage("Cannot execute command. Make sure you have exactly one argument and you are running the command as a player (not from the console).");
        }
        return false;
    }
}
