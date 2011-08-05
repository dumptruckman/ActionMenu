package com.dumptruckman.actionmenu.plugin;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerListener;
//import org.bukkitcontrib.gui.*;
//import org.bukkitcontrib.player.ContribPlayer;


/**
 * @author dumptruckman
 */
public class ActionMenuPlayerListener extends PlayerListener {

    ActionMenuPlugin plugin;

    public ActionMenuPlayerListener(ActionMenuPlugin plugin) {
        this.plugin = plugin;
    }

    public void onItemHeldChange(PlayerItemHeldEvent event) {
        if (event.getPlayer().getItemInHand().getType() != Material.MAP) return;
        plugin.menu.showMenu(event.getPlayer());
    }

    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getItemInHand().getType() != Material.MAP) return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            plugin.menu.cycleMenu();
            plugin.menu.showMenu(event.getPlayer());
        } else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            plugin.menu.doSelectedMenuItem(event.getPlayer());
        }
    }
}
