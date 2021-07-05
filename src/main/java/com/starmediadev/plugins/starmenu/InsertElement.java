package com.starmediadev.plugins.starmenu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class InsertElement extends Element {
    public InsertElement() {
        this(-1);
    }

    public InsertElement(int staticIndex) {
        super(null, staticIndex);
        allowInsert = true;
    }
    
    public abstract void onInsert(Player player, Menu menu, ItemStack itemStack);
    public abstract void onRemove(Player player, Menu menu);
}
