package com.starmediadev.plugins.starmenu.element;

import com.starmediadev.plugins.starmcutils.builder.ItemBuilder;
import com.starmediadev.plugins.starmenu.element.Element;
import org.bukkit.Material;

public class FillerElement extends Element {

    public FillerElement(Material material, int staticIndex) {
        super(ItemBuilder.start(material).setDisplayName("&f").build(), staticIndex);
        this.isReplaceable = true;
    }
}
