package com.starmediadev.plugins.starmenu;

public class Slot {
    
    protected int index; //The slot index for the inventory.
    protected Element element; //The current element of the slot

    public Slot(int index) {
        this.index = index;
    }
}
