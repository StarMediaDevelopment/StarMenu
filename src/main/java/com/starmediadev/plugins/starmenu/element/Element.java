package com.starmediadev.plugins.starmenu.element;

import org.bukkit.inventory.ItemStack;

public class Element {

    protected ItemStack itemStack; //The item of the slot, can be null for an empty one
    protected boolean isStatic; //If this element persists across all pages
    protected int staticIndex = -1; //The index of where this element is when it is static
    protected boolean allowInsert; //Allow inserting of items into this element when it is in a slot
    protected boolean isReplaceable; //Allow replacing this element when building the menu

    public Element(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public Element(ItemStack itemStack, int staticIndex) {
        this.itemStack = itemStack;
        this.staticIndex = staticIndex;
        if (staticIndex > -1) {
            this.isStatic = true;
        }
    }

    public void setStaticIndex(int staticIndex) {
        this.staticIndex = staticIndex;
        this.isStatic = true;
    }

    public boolean isReplaceable() {
        return isReplaceable;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public boolean isStatic() {
        return staticIndex > -1;
    }

    public int getStaticIndex() {
        return staticIndex;
    }

    public boolean isAllowInsert() {
        return allowInsert;
    }
    
    @Override
    public String toString() {
        return itemStack.getType().toString();
    }
}
