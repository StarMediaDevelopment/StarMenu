package com.starmediadev.plugins.starmenu;

import org.apache.logging.log4j.core.layout.PatternLayout;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Button extends Element {
    
    protected ButtonAction leftClickAction, rightClickAction; //The actions used for right and left clicking the item, shift clicking should be handled by the actions
    protected Sound clickSound; //Sound for clicking on the button
    
    public Button(ItemStack itemStack) {
        super(itemStack);
    }
    
    public Button(ItemStack itemStack, Sound sound) {
        super(itemStack);
        this.clickSound = sound;
    }

    public Button(ItemStack itemStack, int staticIndex) {
        super(itemStack, staticIndex);
    }
    
    public void playSound(Player player) {
        if (clickSound != null) {
            player.playSound(player.getLocation(), clickSound, 1F, 1F);
        }
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

    public void setClickSound(Sound clickSound) {
        this.clickSound = clickSound;
    }

    public Sound getClickSound() {
        return clickSound;
    }
}
