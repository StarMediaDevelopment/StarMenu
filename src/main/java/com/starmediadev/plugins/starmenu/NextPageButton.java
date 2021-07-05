package com.starmediadev.plugins.starmenu;

import com.starmediadev.plugins.starmcutils.builder.ItemBuilder;
import org.bukkit.Material;

public class NextPageButton extends Button {

    public NextPageButton(Material material, String color) {
        this(material, color, -1);
    }

    public NextPageButton(Material material, String color, int staticIndex) {
        super(ItemBuilder.start(material).setDisplayName(color + "Next Page").build(), staticIndex);
    }
}
