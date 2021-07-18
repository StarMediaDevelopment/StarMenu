package com.starmediadev.plugins.starmenu.element.button;

import com.starmediadev.plugins.starmcutils.builder.ItemBuilder;
import com.starmediadev.plugins.starmcutils.util.MCUtils;
import com.starmediadev.plugins.starmenu.StarMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;

public class PreviousPageButton extends Button {

    public PreviousPageButton(Material material, String color) {
        this(material, color, -1);
    }

    public PreviousPageButton(Material material, String color, int staticIndex) {
        super(ItemBuilder.start(material).setDisplayName(color + "Previous Page").build(), staticIndex);
        this.leftClickAction = (player, menu, click) -> {
            int totalPages = menu.getTotalPages();
            int currentPage = menu.getCurrentPage();

            if (currentPage == 1) {
                player.sendMessage(MCUtils.color("&cYou are already at the first page."));
                return;
            }

            menu.setCurrentPage(--currentPage);
            Bukkit.getScheduler().runTaskLater(StarMenu.getPlugin(StarMenu.class), () -> player.openInventory(menu.getInventory()), 1L);
        };
    }
}
