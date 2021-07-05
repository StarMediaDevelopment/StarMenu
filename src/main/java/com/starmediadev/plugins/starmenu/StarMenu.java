package com.starmediadev.plugins.starmenu;

import org.bukkit.entity.Player;
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
        Player player = (Player) e.getWhoClicked();
        if (!(e.getClickedInventory().getHolder() instanceof Menu menu))
            return;
        if (e.getSlot() != e.getRawSlot())
            return;

        Slot slot = menu.getSlot(e.getSlot());
        if (slot == null)
            return;
        Element element = slot.getElement();
        if (element == null || !slot.getElement().isAllowInsert()) {
            e.setCancelled(true);
        }
        
        if (element instanceof Button button) {
            button.playSound(player);
            if (e.isLeftClick()) {
                if (button.getLeftClickAction() != null) {
                    button.getLeftClickAction().onClick(player, menu, e.getClick());
                }
            } else if (e.isRightClick()) {
                if (button.getRightClickAction() != null) {
                    button.getRightClickAction().onClick(player, menu, e.getClick());
                }
            }
        }
    }
}
