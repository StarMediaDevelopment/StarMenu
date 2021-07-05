package com.starmediadev.plugins.starmenu;

import org.bukkit.inventory.ItemStack;

//Should probably find a better name, couldn't think of a better one
public class Element {

    protected ItemStack itemStack; //The item of the slot, can be null for an empty one
    protected boolean isStatic; //If this element persists across all pages
}
