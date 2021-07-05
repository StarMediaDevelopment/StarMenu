package com.starmediadev.plugins.starmenu;

import org.bukkit.inventory.ItemStack;

public class Button extends Element {
    
    protected ButtonAction leftClickAction, rightClickAction; //The actions used for right and left clicking the item, shift clicking should be handled by the actions

    public Button(ItemStack itemStack) {
        super(itemStack);
    }

    public Button(ItemStack itemStack, int staticIndex) {
        super(itemStack, staticIndex);
    }

    public void setLeftClickAction(ButtonAction leftClickAction) {
        this.leftClickAction = leftClickAction;
    }

    public void setRightClickAction(ButtonAction rightClickAction) {
        this.rightClickAction = rightClickAction;
    }

    public ButtonAction getLeftClickAction() {
        return leftClickAction;
    }

    public ButtonAction getRightClickAction() {
        return rightClickAction;
    }
}
