package com.alticator.megalosmp.megalosmpcore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

public class Exchange implements CommandExecutor {

    Material argMaterial;
    int argMaterialPrice;

    // Prices in gold ingots
    // Note that some prices hardcoded in the parseMaterial() method.
    private final int logPrice = 1;
    private final int enderPearlPrice = 2;
    private final int diamondPrice = 24;

    private final int concretePrice = 3;

    /////////////////////////

    public void parseMaterial(String arg) {
        Material pay;
        int price = 0;
        switch (arg.toLowerCase().replace("_", "")) {
            case "acacialog":
                pay = Material.ACACIA_LOG;
                price = logPrice;
                break;
            case "birchlog":
                pay = Material.BIRCH_LOG;
                price = logPrice;
                break;
            case "mangrovelog":
                pay = Material.MANGROVE_LOG;
                price = logPrice;
                break;
            case "junglelog":
                pay = Material.JUNGLE_LOG;
                price = logPrice;
                break;
            case "darkoaklog":
                pay = Material.DARK_OAK_LOG;
                price = logPrice;
                break;
            case "oaklog":
                pay = Material.OAK_LOG;
                price = logPrice;
                break;
            case "sprucelog":
                pay = Material.SPRUCE_LOG;
                price = logPrice;
                break;
            case "enderpearl":
                pay = Material.ENDER_PEARL;
                price = enderPearlPrice;
                break;
            case "diamond":
                pay = Material.DIAMOND;
                price = diamondPrice;
                break;
            case "iron":
            case "ironingot":
                pay = Material.IRON_INGOT;
                price = 16;
                break;
            case "cobblestone":
                pay = Material.COBBLESTONE;
                price = 1;
                break;
            case "stone":
                pay = Material.STONE;
                price = 2;
                break;
            case "smoothstone":
                pay = Material.SMOOTH_STONE;
                price = 3;
                break;
            case "stonebricks":
                pay = Material.STONE_BRICKS;
                price = 2;
                break;
            case "whiteconcrete":
                pay = Material.WHITE_CONCRETE;
                price = concretePrice;
                break;
            case "lightblueconcrete":
                pay = Material.LIGHT_BLUE_CONCRETE;
                price = concretePrice;
                break;
            case "cyanconcrete":
                pay = Material.CYAN_CONCRETE;
                price = concretePrice;
                break;
            case "yellowconcrete":
                pay = Material.YELLOW_CONCRETE;
                price = concretePrice;
                break;
            case "grayconcrete":
                pay = Material.GRAY_CONCRETE;
                price = concretePrice;
                break;
            case "asphalt":
            case "cyanterracotta":
                pay = Material.CYAN_TERRACOTTA;
                price = concretePrice;
                break;
            case "netheriteingot":
                pay = Material.NETHERITE_INGOT;
                price = 36;
                break;
            case "coal":
                pay = Material.COAL;
                price = 3;
                break;
            case "sealantern":
                pay = Material.SEA_LANTERN;
                price = 3;
                break;
            case "enderchest":
                pay = Material.ENDER_CHEST;
                price = 128;
                break;
            case "glass":
                pay = Material.GLASS;
                price = 2;
                break;
            case "whitestainedglass":
                pay = Material.WHITE_STAINED_GLASS;
                price = 2;
                break;
            case "blackstainedglass":
                pay = Material.BLACK_STAINED_GLASS;
                price = 2;
                break;
            case "wool":
                pay = Material.WHITE_WOOL;
                price = 1;
                break;
            case "bed":
                pay = Material.PURPLE_BED;
                price = 8;
                break;
            case "villagerspawnegg":
                pay = Material.VILLAGER_SPAWN_EGG;
                price = 1024;
                break;
            case "emeraldblock":
                pay = Material.EMERALD_BLOCK;
                price = 32;
                break;
            default:
                pay = null;

            // When updating, also add the new items to ExchangeTabCompleter
        }
        argMaterial = pay;
        argMaterialPrice = price;

    }

    private boolean buy(Player player, Material item, int quantity, int price) {
        int playerGoldBalance = Util.countPlayerMaterial(player, Material.GOLD_INGOT);
        int transactionGold = Math.round(price * quantity);
        if (playerGoldBalance >= transactionGold) {
            Util.take(player, Material.GOLD_INGOT, transactionGold);
            Util.give(player, item, quantity);
            player.sendMessage(ChatColor.GREEN + "Bought " + quantity + " " + item.toString() + "(s) with " + transactionGold + " Gold.");
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "You don't have enough gold. (You have: " + playerGoldBalance + ", Required: " + transactionGold + ")");
            return false;
        }
    }

    private boolean sell(Player player, Material item, int quantity, int price) {
        int playerItemQuantity = Util.countPlayerMaterial(player, item);
        if (playerItemQuantity >= quantity) {
            int goldValue = quantity * price;
            Util.take(player, item, quantity);
            Util.give(player, Material.GOLD_INGOT, goldValue);
            player.sendMessage(ChatColor.GREEN + "Sold " + quantity + " " + item.toString() + "(s) for " + goldValue + " Gold.");
            return true;
        } else {
            player.sendMessage(ChatColor.RED + "You don't have enough " + item.toString() + ". (You have: " + playerItemQuantity + ", Required: " + quantity + ")");
            return false;
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 2) {
                if (args[0].equalsIgnoreCase("buy")) {
                    String quantity = "1";
                    int quantityInt;
                    if (args.length >= 3) {
                        quantity = args[2];
                    }
                    try {
                        quantityInt = Integer.parseInt(quantity);
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "Invalid Quantity (NumberFormatException)");
                        return false;
                    }
                    parseMaterial(args[1]);
                    if (argMaterial != null) {
                        buy(player, argMaterial, quantityInt, argMaterialPrice);
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid Material");
                        return false;
                    }
                } else if (args[0].equalsIgnoreCase("sell")) {
                    String quantity = "1";
                    int quantityInt;
                    if (args.length >= 3) {
                        quantity = args[2];
                    }
                    try {
                        quantityInt = Integer.parseInt(quantity);
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "Invalid Quantity (NumberFormatException)");
                        return false;
                    }
                    parseMaterial(args[1]);
                    if (argMaterial != null) {
                        sell(player, argMaterial, quantityInt, argMaterialPrice);
                        return true;
                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid Material");
                        return false;
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "Not enough arguments.");
            }
        } else {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        return false;
    }
}
