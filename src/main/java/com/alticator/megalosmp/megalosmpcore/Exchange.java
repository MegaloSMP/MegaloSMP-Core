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
    private final int logPrice = 1;
    private final int enderPearlPrice = 2;
    private final int diamondPrice = 48;

    /////////////////////////

    public void parseMaterial(String arg) {
        Material pay;
        int price = 0;
        switch (arg.toLowerCase().replace("_", "")) {
            case "gold":
            case "goldingot":
                pay = Material.GOLD_INGOT;
                break;
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
            default:
                pay = null;
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
            player.sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "Bought " + quantity + " " + item.toString() + "(s) with " + transactionGold + " Gold.");
            return true;
        } else {
            player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "You don't have enough gold. (You have: " + playerGoldBalance + ", Required: (" + transactionGold + ")");
            return false;
        }
    }

    private boolean sell(Player player, Material item, int quantity, int price) {
        int playerItemQuantity = Util.countPlayerMaterial(player, item);
        if (playerItemQuantity >= quantity) {
            int goldValue = quantity * price;
            Util.take(player, item, quantity);
            Util.give(player, Material.GOLD_INGOT, goldValue);
            player.sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "Sold " + quantity + " " + item.toString() + "(s) for " + goldValue + " Gold.");
            return true;
        } else {
            player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "You don't have enough " + item.toString() + ". (You have: " + playerItemQuantity + ", Required: (" + quantity + ")");
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
                        player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "Invalid Quantity (NumberFormatException)");
                        return false;
                    }
                    parseMaterial(args[1]);
                    if (argMaterial != null) {
                        buy(player, argMaterial, quantityInt, argMaterialPrice);
                        return true;
                    } else {
                        player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "Invalid Material");
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
                        player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "Invalid Quantity (NumberFormatException)");
                        return false;
                    }
                    parseMaterial(args[1]);
                    if (argMaterial != null) {
                        sell(player, argMaterial, quantityInt, argMaterialPrice);
                        return true;
                    } else {
                        player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "Invalid Material");
                        return false;
                    }
                }
            } else {
                player.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "Not enough arguments.");
            }
        } else {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        return false;
    }
}
