package com.starmediadev.plugins.starmenu.element.button;

import com.starmediadev.plugins.starmenu.gui.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public interface ButtonAction {
    void onClick(Player player, Menu menu, ClickType click);
}
