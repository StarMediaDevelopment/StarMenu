package com.starmediadev.plugins.starmenu.manager;

import com.starmediadev.plugins.starmenu.gui.Menu;
import org.bukkit.plugin.java.JavaPlugin;

public class DefaultMenuProvider extends MenuProvider {

    public DefaultMenuProvider(JavaPlugin plugin, String name, String title) {
        super(plugin, name, title);
    }

    public Menu create(int rows) {
        return new Menu(plugin, name, title, rows);
    }
    
    public Menu create(String title, int rows) {
        return new Menu(plugin, name, title, rows);
    }
}