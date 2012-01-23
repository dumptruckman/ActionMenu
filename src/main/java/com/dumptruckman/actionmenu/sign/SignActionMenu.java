package com.dumptruckman.actionmenu.sign;

import com.dumptruckman.actionmenu.ActionMenu;
import com.dumptruckman.actionmenu.ActionMenuItem;
import com.dumptruckman.actionmenu.IncompatibleMenuItemException;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;

/**
 * @author dumprtuckman
 */
public abstract class SignActionMenu extends ActionMenu {

    private Block block;

    /**
     * Create a sign based ActionMenu.
     *
     * @param block Block that this menu is assigned to.  It must be a Sign.
     */
    public SignActionMenu(Block block) {
        this.block = block;
    }

    /**
     * Returns the block associated with this menu.
     *
     * @return Block associated with this menu.
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Sets the block this menu is assigned to.
     *
     * @param block Block menu will be assigned to.
     */
    public void setBlock(Block block) {
        this.block = block;
    }

    /**
     * Gets the sign associated with this menu.
     *
     * @return Sign associated with this menu or null if block is no longer a Sign.
     */
    public Sign getSign() {
        BlockState blockState = this.getBlock().getState();
        if (blockState instanceof Sign)
            return (Sign) blockState;
        return null;
    }

    /**
     * Adds a menu item to the end of the menu.
     *
     * @param item Menu item to add to the menu.
     * @return Index of the new menu item.
     */
    @Override
    public Integer add(ActionMenuItem item) {
        if (!(item instanceof SignActionMenuItem))
            throw new IncompatibleMenuItemException("You may only add SignActionMenuItem to this menu!");
        return super.add(item);
    }
}
