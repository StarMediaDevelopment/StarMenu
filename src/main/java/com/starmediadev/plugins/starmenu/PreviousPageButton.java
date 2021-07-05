package com.starmediadev.plugins.starmenu;

import com.starmediadev.plugins.starmcutils.builder.ItemBuilder;
import org.bukkit.Material;

public class PreviousPageButton extends Button {

    public PreviousPageButton(Material material, String color) {
        this(material, color, -1);
    }

    public PreviousPageButton(Material material, String color, int staticIndex) {
        super(ItemBuilder.start(material).setDisplayName(color + "Previous Page").build(), staticIndex);
    }
}
