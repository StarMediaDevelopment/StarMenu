package com.starmediadev.plugins.starmenu;

import com.starmediadev.plugins.starmcutils.builder.ItemBuilder;
import com.starmediadev.plugins.starmcutils.util.MCUtils;
import com.starmediadev.plugins.starmcutils.util.MaterialNames;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Material.*;

public class StarMenu extends JavaPlugin implements Listener {

    private MenuManager menuManager;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        
        menuManager = new MenuManager();
        getServer().getServicesManager().register(MenuManager.class, menuManager, this, ServicePriority.Highest);
        menuManager.register(this, new DefaultMenuProvider(this, "default", "default"));
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        getServer().getScheduler().runTaskLater(this, () -> {
            Menu menu = new Menu(this, "test", "Testing Menu", 6);
            Button nextPage = new NextPageButton(ARROW, "&e", 59);
            Button previousPage = new PreviousPageButton(ARROW, "&e", 57);
            Element filterElement = new InsertElement(8) {
                public void onInsert(Player player, Menu menu, ItemStack itemStack) {
                    player.sendMessage(MCUtils.color("&aInserted an item " + itemStack.getType()));
                }

                public void onRemove(Player player, Menu menu) {
                    player.sendMessage(MCUtils.color("&aRemoved an item from the slot"));
                }
            };
            menu.addElements(nextPage, previousPage, filterElement);
            menu.setFillerRange(WHITE_STAINED_GLASS_PANE, 54, 62);
            menu.setFillerRange(WHITE_STAINED_GLASS_PANE, 0, 8);
            menu.setFillerSlots(WHITE_STAINED_GLASS_PANE, 9, 17, 18, 26, 27, 35, 36, 44, 45, 53);

            for (Material material : Material.values()) {
                String name = material.name().toLowerCase();
                if (name.contains("pickaxe") || name.contains("axe") || name.contains("sword") || name.contains("hoe") || name.contains("shovel")) {
                    menu.addElement(new Element(ItemBuilder.start(material).setDisplayName("&b" + MaterialNames.getName(material)).build()));
                }
            }
            
            e.getPlayer().openInventory(menu.getInventory());
        }, 20L);
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
        } else if (element instanceof InsertElement insertElement) {
            ItemStack item = e.getInventory().getItem(e.getSlot());
            if (item != null) {
                insertElement.onInsert(player, menu, item);
            } else {
                insertElement.onRemove(player, menu);
            }
        }
    }
}
