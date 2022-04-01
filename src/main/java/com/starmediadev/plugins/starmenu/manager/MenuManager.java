package com.starmediadev.plugins.starmenu.manager;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the registered menus
 * 
 * This is not necessary to use this library, but this allows a way to get a menu from another plugin without having to directly depend on that plugin
 * And can just use the generic getPlugin methods in the Bukkit Plugin Manager
 */
public class MenuManager {
    
    private Map<String, MenuProvider> menus = new HashMap<>();
    
    /**
     * Registers a menu
     * @param plugin The plugin of the menu
     * @param provider The provider of the menu
     */
    public void register(JavaPlugin plugin, MenuProvider provider) {
        this.menus.put(plugin.getName() + ":" + provider.getName(), provider);
    }
    
    /**
     * Gets a menu based on a plugin 
     * @param plugin The plugin
     * @param menuName The name of the menu
     * @return The Menu Provider
     */
    public MenuProvider getMenu(JavaPlugin plugin, String menuName) {
        for (MenuProvider value : this.menus.values()) {
            if (value.getName().equalsIgnoreCase(menuName) && value.getPlugin().equals(plugin)) {
                return value;
            }
        }
        return null;
    }
}
