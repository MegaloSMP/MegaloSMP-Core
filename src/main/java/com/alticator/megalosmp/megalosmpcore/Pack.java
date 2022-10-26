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

            int dyePrice = 16;

            switch (args[0].toLowerCase().replace("_", "")) {
                case "beef":
                    item = new ItemStack(Material.COOKED_BEEF, 64);
                    price = 16;
                    break;
                case "glow":
                    item = new ItemStack(Material.GLOW_INK_SAC, 64);
                    price = dyePrice;
                    break;
                case "whitedye":
                    item = new ItemStack(Material.WHITE_DYE, 64);
                    price = dyePrice;
                    break;
                case "greendye":
                    item = new ItemStack(Material.GREEN_DYE, 64);
                    price = dyePrice;
                    break;
                case "reddye":
                    item = new ItemStack(Material.RED_DYE, 64);
                    price = dyePrice;
                    break;
                case "yellowdye":
                    item = new ItemStack(Material.YELLOW_DYE, 64);
                    price = dyePrice;
                    break;
                case "cyandye":
                    item = new ItemStack(Material.CYAN_DYE, 64);
                    price = dyePrice;
                    break;
                case "magentadye":
                    item = new ItemStack(Material.MAGENTA_DYE, 64);
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
                    break;
                case "destroyerpick":
                    item = new ItemStack(Material.NETHERITE_PICKAXE, 1);
                    item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 20);
                    item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                    price = 1024;
                    break;
                case "ultimatesword":
                    item = new ItemStack(Material.NETHERITE_SWORD, 1);
                    item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 10);
                    price = 1024;
                    break;
                case "knockbackstick":
                    item = new ItemStack(Material.STICK, 1);
                    item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 50);
                    price = 512;
                    break;
                case "ultimateaxe":
                    item = new ItemStack(Material.NETHERITE_AXE, 1);
                    item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
                    item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                    price = 128;
                    break;
                case "ultimateshovel":
                    item = new ItemStack(Material.NETHERITE_SHOVEL, 1);
                    item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
                    item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                    price = 128;
                    break;
                case "ultimatehoe":
                    item = new ItemStack(Material.NETHERITE_HOE, 1);
                    item.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
                    price = 128;
                    break;
                case "maximumsword":
                    item = new ItemStack(Material.NETHERITE_SWORD, 1);
                    item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 255);
                    item.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 10);
                    price = 2048;
                    break;
                case "scaffolding":
                    item = new ItemStack(Material.SCAFFOLDING, 64);
                    price = 16;
                    break;
                case "torch":
                    item = new ItemStack(Material.TORCH, 64);
                    price = 16;
                    break;
                default:
                    player.sendMessage(ChatColor.RED + "Invalid pack name.");
                    return false;

                // When updating, also add the new items to PackTabCompleter
            }

            if (command.getName().equalsIgnoreCase("pack")) {
                if (Util.countPlayerMaterial(player, Material.GOLD_INGOT) >= price) {
                    Util.take(player, Material.GOLD_INGOT, price);
                    player.getInventory().addItem(item);
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Not enough gold. (You have: " + Util.countPlayerMaterial(player, Material.GOLD_INGOT) + ", needed: " + price + ")");
                }
            } else if (command.getName().equalsIgnoreCase("packprice")) {
                player.sendMessage(ChatColor.GREEN + "Price for the [" + args[0] + "] pack: " + ChatColor.YELLOW + "" + price + " Gold");
                return true;
            }

        } else {
            sender.sendMessage("Cannot execute command. Make sure you have exactly one argument and you are running the command as a player (not from the console).");
        }
        return false;
    }
}
