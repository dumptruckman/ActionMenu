package com.dumptruckman.actionmenu;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author dumptruckman
 */
public abstract class PlayerActionMenuItem extends ActionMenuItem {

    private Player player;

    public PlayerActionMenuItem() {
        this("");
    }

    public PlayerActionMenuItem(String text) {
        super(text);
    }

    /**
     * Sets the player for this sign's menu interaction.
     * @param sender Player interacting with the sign menu.
     */
    protected void setInteracting(CommandSender sender) {
        this.player = (Player)sender;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * When the sign is cycled it sets the player causing the cycle event as the player interacting with the sign.
     * @param sender Whoever caused the cycle event.  Could be null.
     */
    //@Override
    //protected void onCycle(CommandSender sender) {
    //    setInteractingPlayer((sender instanceof Player ? (Player) sender : null));
    //}

    /**
     * When the menu item is selected it sets the player causing the cycle event as the player interacting with the sign.
     * @param sender Whoever caused the selection event.  Could be null.
     */
    //@Override
    //protected void onSelect(CommandSender sender) {
    //    setInteractingPlayer((sender instanceof Player ? (Player) sender : null));
    //}
}
