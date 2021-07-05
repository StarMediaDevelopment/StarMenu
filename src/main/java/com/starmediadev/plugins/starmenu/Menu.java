package com.starmediadev.plugins.starmenu;

import com.starmediadev.plugins.starmcutils.util.MCUtils;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Menu implements InventoryHolder {
    
    protected JavaPlugin plugin; //The plugin that registered this menu
    protected String name; //Can be used to get from a cache/used by other plugins if needed/wanted
    protected String title; //The title for this menu, this is displayed in the title of the menu in game
    protected int rows; //Must be multiplied by 9
    protected Map<Integer, Element> elements = new HashMap<>(); //All elements provided to this menu. This is used to build pages
    protected Map<Integer, Slot> slots = new HashMap<>(); //All slots for the menu, this is auto-generated and is used for setting and building the menu
    protected int currentPage;
    
    public Menu(JavaPlugin plugin, String name, String title, int rows) {
        this.plugin = plugin;
        this.name = name;
        this.title = title;
        this.rows = rows;
        
        if (rows > 6) {
            throw new IllegalArgumentException("Too many rows for the menu " + name);
        }
        int totalSlots = rows * 9;
        for (int i = 0; i < totalSlots; i++) {
            slots.put(i, new Slot(i));
        }
    }

    public Inventory getInventory() {
        Inventory inv = Bukkit.createInventory(this, rows * 9, MCUtils.color(title));
        return inv;
    }
}
