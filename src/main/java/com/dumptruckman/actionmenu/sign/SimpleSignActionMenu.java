package com.dumptruckman.actionmenu.sign;

import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class SimpleSignActionMenu extends SignActionMenu {
    
    public SimpleSignActionMenu(Block block) {
        super(block);
    }

    /**
     * Cause this menu to show on the sign.  If a sender is specified it will send a false block update to them first to ensure the text is displayed.
     * @param sender CommandSender to show menu to.  May be set to null.
     */
    public void showMenu(CommandSender sender) {
        showSelectedMenuItem(sender);
    }

    /**
     * Shows a single menu item on the sign.  Since signs are small, they are unable to hold much text
     * @param sender
     */
    private void showSelectedMenuItem(CommandSender sender) {
        Sign sign = this.getSign();
        if (sign == null) {
            Logger.getLogger("Minecraft.ActionMenu").severe("Tried to show a SignActionMenu on a non-sign block.");
            return;
        }
        for (int i = 0; i < 4; i++) {
            sign.setLine(i, ((SignActionMenuItem) this.getSelectedItem()).getLine(i));
        }
        if (sender instanceof Player && sender != null) {
            ((Player) sender).sendBlockChange(sign.getBlock().getLocation(), 0, (byte) 0);
        }
        sign.update(true);
    }
}
