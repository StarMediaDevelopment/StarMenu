package com.starmediadev.plugins.starmenu;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {
    
    private Map<String, MenuProvider> menus = new HashMap<>();
    
    public void register(JavaPlugin plugin, MenuProvider provider) {
        this.menus.put(plugin.getName() + ":" + provider.getName(), provider);
    }
}
