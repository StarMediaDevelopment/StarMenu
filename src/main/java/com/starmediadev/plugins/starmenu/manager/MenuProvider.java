package com.starmediadev.plugins.starmenu.manager;

import com.starmediadev.plugins.starmenu.gui.Menu;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class MenuProvider {
    
    protected JavaPlugin plugin;
    protected String name, title;

    public MenuProvider(JavaPlugin plugin, String name, String title) {
        this.plugin = plugin;
        this.name = name;
        this.title = title;
    }

    public abstract Menu create(int rows);
    public abstract Menu create(String title, int rows);

    public String getName() {
        return name;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}
