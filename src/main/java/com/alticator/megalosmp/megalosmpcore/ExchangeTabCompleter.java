package com.alticator.megalosmp.megalosmpcore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ExchangeTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<String>();
        if (args.length == 1) {
            String[] options = {"buy", "sell"};
            for (String i : options) {
                if (i.startsWith(args[0].toLowerCase())) {
                    suggestions.add(i);
                }
            }
        } else if (args.length == 2) {
            String[] options = {"acacia_log", "birch_log", "mangrove_log", "jungle_log", "dark_oak_log",
                    "oak_log", "spruce_log", "ender_pearl", "diamond", "iron", "iron_ingot", "asphalt", "cyan_terracotta",
                    "cobblestone", "stone", "smooth_stone", "stone_bricks", "white_concrete", "light_blue_concrete",
                    "cyan_concrete", "yellow_concrete", "netherite_ingot", "coal", "sea_lantern", "ender_chest", "glass",
                    "white_stained_glass", "black_stained_glass", "bed", "gray_concrete", "villager_spawn_egg",
                    "emerald_block", "wool"};
            for (String i : options) {
                if (i.startsWith(args[1].toLowerCase())) {
                    suggestions.add(i);
                }
            }
        }
        return suggestions;
    }
}
