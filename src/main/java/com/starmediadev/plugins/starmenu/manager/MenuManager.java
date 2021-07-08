package com.starmediadev.plugins.starmenu.manager;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {
    
    private Map<String, MenuProvider> menus = new HashMap<>();
    
    public void register(JavaPlugin plugin, MenuProvider provider) {
        this.menus.put(plugin.getName() + ":" + provider.getName(), provider);
    }
    
    public MenuProvider getMenu(JavaPlugin plugin, String menuName) {
        for (MenuProvider value : this.menus.values()) {
            if (value.getName().equalsIgnoreCase(menuName) && value.getPlugin().equals(plugin)) {
                return value;
            }
        }
        return null;
    }
}
