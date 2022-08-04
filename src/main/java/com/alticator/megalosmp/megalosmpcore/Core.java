package com.alticator.megalosmp.megalosmpcore;

import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginCommand("exchange").setExecutor(new Exchange());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
