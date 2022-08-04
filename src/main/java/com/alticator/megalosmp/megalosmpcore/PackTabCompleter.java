package com.alticator.megalosmp.megalosmpcore;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PackTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> suggestions = new ArrayList<String>();
        if (args.length == 1) {
            String[] packs = {"beef", "glow", "cyan_dye", "white_dye", "green_dye", "red_dye", "UltimatePick", "ProPick"}; // Must match the list in the Pack class
            for (String i : packs) {
                if (i.replace("_", "").startsWith(args[0].toLowerCase().replace("_", ""))) {
                    suggestions.add(i);
                }
            }
        }
        return suggestions;
    }
}
