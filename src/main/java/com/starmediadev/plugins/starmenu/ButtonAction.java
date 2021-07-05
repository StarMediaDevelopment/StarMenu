package com.starmediadev.plugins.starmenu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public interface ButtonAction {
    void onClick(Player player, Menu menu, ClickType click);
}
