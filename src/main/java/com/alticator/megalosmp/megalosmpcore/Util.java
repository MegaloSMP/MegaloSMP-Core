package com.alticator.megalosmp.megalosmpcore;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.ArrayList;

public class Util {
    public static ItemStack[] stackItems(Material type, int amount) {
        int count = amount;
        ArrayList<ItemStack> stackArrayList = new ArrayList<ItemStack>();
        while (count > 0) {
            if (count >= type.getMaxStackSize()) {
                stackArrayList.add(new ItemStack(type, type.getMaxStackSize()));
                count -= type.getMaxStackSize();
            } else {
                stackArrayList.add(new ItemStack(type, count));
                count = 0;
            }
        }
        ItemStack[] stacks = stackArrayList.toArray(new ItemStack[stackArrayList.size()]);
        return stacks;
    }

    // Count how much of an item the player has
    // Returns the count
    public static int countPlayerMaterial(Player player, Material type) {
        int count = 0;
        for (ItemStack stack : player.getInventory().getContents()) {
            if (stack != null && stack.getType() == type) {
                count += stack.getAmount();
            }
        }
        return count;
    }

    public static void give(Player player, Material type, int amount) {
        ItemStack[] stacks = stackItems(type, amount);
        PlayerInventory inventory = player.getInventory();
        for (ItemStack stack : stacks) {
            inventory.addItem(stack);
        }
    }

    // Takes an amount of specified items from the player's inventory
    // Returns true if successful, false if player doesn't have enough
    public static boolean take(Player player, Material type, int amount) {
        if (player.getInventory().contains(type)) {
            ItemStack[] inventoryContents = player.getInventory().getContents();
            int taken = 0;
            for (ItemStack i : inventoryContents) {
                if (i != null && i.getType() == type) {
                    if (i.getAmount() >= amount - taken) {
                        i.setAmount(i.getAmount() - (amount - taken));
                        taken = amount;
                    } else if (i.getAmount() < amount - taken) { // Amount 127, taken 0
                        taken += i.getAmount();
                        i.setAmount(0);
                    }
                }
            }
            if (taken == amount) {
                player.getInventory().setContents(inventoryContents);
                player.updateInventory();
                return true;
            }
        }

        return false;
    }
}
