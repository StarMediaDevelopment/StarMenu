package com.starmediadev.plugins.starmenu;

import org.bukkit.inventory.ItemStack;

public class InsertElement extends Element {
    public InsertElement(ItemStack itemStack) {
        this(itemStack, -1);
    }

    public InsertElement(ItemStack itemStack, int staticIndex) {
        super(itemStack, staticIndex);
        allowInsert = true;
    }
}
