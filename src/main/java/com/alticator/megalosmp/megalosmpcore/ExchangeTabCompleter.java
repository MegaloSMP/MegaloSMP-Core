package com.alticator.megalosmp.megalosmpcore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ExchangeTabCompleter implements TabCompleter {

    public static String[] options = {"acacia_log", "birch_log", "mangrove_log", "jungle_log", "dark_oak_log",
            "oak_log", "spruce_log", "ender_pearl", "diamond", "iron", "iron_ingot", "asphalt", "cyan_terracotta",
            "cobblestone", "stone", "smooth_stone", "stone_bricks", "white_concrete", "light_blue_concrete",
            "cyan_concrete", "yellow_concrete", "red_concrete", "netherite_ingot", "coal", "sea_lantern", "chest", "ender_chest", "glass",
            "white_stained_glass", "black_stained_glass", "bed", "gray_concrete", "villager_spawn_egg",
            "emerald_block", "wool", "bed", "villager_spawn_egg", "emerald_block", "stone_brick_wall", "end_rod",
            "andesite", "diorite", "beacon"};
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<String>();
        if (command.getName().equalsIgnoreCase("exchange")) {
            if (args.length == 1) {
                String[] options = {"buy", "sell"};
                for (String i : options) {
                    if (i.replace("_", "").contains(args[0].toLowerCase().replace("_", ""))) {
                        suggestions.add(i);
                    }
                }
            } else if (args.length == 2) {
                for (String i : options) {
                    if (i.replace("_", "").contains(args[1].toLowerCase().replace("_", ""))) {
                        suggestions.add(i);
                    }
                }
            }
        } else if (command.getName().equalsIgnoreCase("exchangeprice")) {
            if (args.length == 1) {
                for (String i : options) {
                    if (i.replace("_", "").contains(args[0].toLowerCase().replace("_", ""))) {
                        suggestions.add(i);
                    }
                }
            }
        }

        return suggestions;
    }
}
