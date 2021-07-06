package com.starmediadev.plugins.starmenu;

import org.bukkit.inventory.ItemStack;

public class Element {

    protected ItemStack itemStack; //The item of the slot, can be null for an empty one
    protected boolean isStatic; //If this element persists across all pages
    protected int staticIndex; //The index of where this element is when it is static
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

    public boolean isReplaceable() {
        return isReplaceable;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public int getStaticIndex() {
        return staticIndex;
    }

    public boolean isAllowInsert() {
        return allowInsert;
    }
}
