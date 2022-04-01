package com.starmediadev.plugins.starmenu.element;

import com.starmediadev.plugins.starmcutils.builder.ItemBuilder;
import org.bukkit.Material;

/**
 * Represents an element that fills in spaces in a Menu
 * Please do not use this class directly and use the Menu.setFillerRange() or Menu.setFillerSlots methods
 */
public class FillerElement extends Element {
    public FillerElement(Material material, int staticIndex) {
        super(ItemBuilder.start(material).displayName("&f").build(), staticIndex);
        this.isReplaceable = true;
    }
}
