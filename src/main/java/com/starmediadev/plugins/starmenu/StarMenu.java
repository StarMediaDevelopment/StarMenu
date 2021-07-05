package com.starmediadev.plugins.starmenu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class StarMenu extends JavaPlugin implements Listener {

    private MenuManager menuManager;
    
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        
        
        menuManager = new MenuManager();
        getServer().getServicesManager().register(MenuManager.class, menuManager, this, ServicePriority.Highest);
        menuManager.register(this, new DefaultMenuProvider(this, "default", "default"));
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        //Handle clicking of the inventories and passing to the menus
    }
}
