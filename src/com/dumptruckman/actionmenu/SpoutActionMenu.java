package com.dumptruckman.actionmenu;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkitcontrib.gui.InGameHUD;
import org.bukkitcontrib.gui.Widget;
import org.bukkitcontrib.player.ContribCraftPlayer;
import org.bukkitcontrib.player.ContribPlayer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dumptruckman
 */
public class SpoutActionMenu extends ActionMenu {

    private Map<ActionMenuItem, Widget> widgetMap = new HashMap<ActionMenuItem, Widget>();

    public void showMenu(CommandSender sender) {
        if (!(sender instanceof Player)) return;
        ContribPlayer cPlayer = ContribCraftPlayer.getContribPlayer((Player)sender);
        InGameHUD hud = cPlayer.getMainScreen();

        for (String header : getHeader()) {
            hud.attachWidget()
        }
        for (ActionMenuItem menuItem : getContents()) {

        }
    }

    public Integer addMenuItem(ActionMenuItem item) {

    }
}
