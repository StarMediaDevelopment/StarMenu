package com.starmediadev.plugins.starmenu;

import com.starmediadev.plugins.starmcutils.builder.ItemBuilder;
import com.starmediadev.plugins.starmcutils.util.MCUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;

public class NextPageButton extends Button {

    public NextPageButton(Material material, String color) {
        this(material, color, -1);
    }

    public NextPageButton(Material material, String color, int staticIndex) {
        super(ItemBuilder.start(material).setDisplayName(color + "Next Page").build(), staticIndex);
        this.leftClickAction = (player, menu, click) -> {
            System.out.println("Next page left click");
            int totalPages = menu.getTotalPages();
            int currentPage = menu.getCurrentPage();

            if (currentPage == totalPages) {
                player.sendMessage(MCUtils.color("&cYou are already at the last page."));
                return;
            }

            menu.setCurrentPage(++currentPage);
            Bukkit.getScheduler().runTaskLater(StarMenu.getPlugin(StarMenu.class), () -> player.openInventory(menu.getInventory()), 1L);
        };
    }
}
