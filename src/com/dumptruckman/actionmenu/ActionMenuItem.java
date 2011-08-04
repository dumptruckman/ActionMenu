package com.dumptruckman.actionmenu;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author dumptruckman
 */
public abstract class ActionMenuItem implements Runnable {

    private String text;
    private CommandSender sender;

    /**
     * Creates a menu item with no text.
     */
    public ActionMenuItem() {
        this("");
    }

    /**
     * Creates a menu item with specified text.
     * @param text Text for menu item.
     */
    public ActionMenuItem(String text) {
        this.text = text;
    }

    /**
     * Empty method.  Used to update menu items in some way.
     */
    public void update() {

    }

    /**
     * Empty method.  Called whenever the menu is cycled.
     * @param sender Whoever caused the cycle event.  Could be null.
     */
    //protected void onCycle(CommandSender sender) {

    //}

    /**
     * Empty method.  Called whenever the menu item becomes selected.
     * @param sender Whoever caused the selection event.  Could be null.
     */
    //protected void onSelect(CommandSender sender) {

    //}

    /**
     * When the sign is cycled it sets the player causing the cycle event as the player interacting with the sign.
     * @param sender Whoever caused the cycle event.  Could be null.
     */
    protected void onCycle(CommandSender sender) {
        setInteracting(sender);
    }

    /**
     * When the menu item is selected it sets the player causing the cycle event as the player interacting with the sign.
     * @param sender Whoever caused the selection event.  Could be null.
     */
    protected void onSelect(CommandSender sender) {
        setInteracting(sender);
    }

    /**
     * Sets the menu item's text.
     * @param text Text for menu item.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the menu item's text.
     * @return Text of menu item.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the player for this menu item's interaction.
     * @param sender CommandSender interacting with the menu.
     */
    protected void setInteracting(CommandSender sender) {
        this.sender = sender;
    }

    public CommandSender getSender() {
        return sender;
    }
}
