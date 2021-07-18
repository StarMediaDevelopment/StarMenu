package com.starmediadev.plugins.starmenu.element;

import com.starmediadev.plugins.starmenu.gui.Menu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class InsertElement extends Element {
    
    protected boolean keepOnPageMove = false;
    
    public InsertElement() {
        this(-1);
    }

    public InsertElement(int staticIndex) {
        super(null, staticIndex);
        allowInsert = true;
    }

    public InsertElement(int staticIndex, boolean keepOnPageMove) {
        super(null, staticIndex);
        allowInsert = true;
        this.keepOnPageMove = keepOnPageMove;
    }
    
    public boolean keepOnPageMove() {
        return keepOnPageMove;
    }
    
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public abstract void onInsert(Player player, Menu menu, ItemStack itemStack);
    public void onRemove(Player player, Menu menu) {
        
    }
}
