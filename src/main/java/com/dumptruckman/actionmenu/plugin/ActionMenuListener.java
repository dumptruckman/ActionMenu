package com.dumptruckman.actionmenu.plugin;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapRenderer;


/**
 * @author dumptruckman
 */
public class ActionMenuListener implements Listener {

    private ActionMenuPluginOld plugin;

    public ActionMenuListener(ActionMenuPluginOld plugin) {
        this.plugin = plugin;
    }

    /**
     * Handles PlayerItemHeldChangeEvents for this plugin.
     *
     * @param event the PlayerItemHeldChangeEvent.
     */
    @EventHandler(event = PlayerItemHeldEvent.class, priority = org.bukkit.event.EventPriority.NORMAL)
    public void onItemHeldChange(PlayerItemHeldEvent event) {
        if (event.getPlayer().getItemInHand().getType() != Material.MAP) return;
        plugin.menu.showMenu(event.getPlayer());
    }

    /**
     * Handles PlayerInteractEvents for this plugin.
     *
     * @param event the PlayerInteractEvent.
     */
    @EventHandler(event = PlayerInteractEvent.class, priority = org.bukkit.event.EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getPlayer().getItemInHand().getType() != Material.MAP) return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            plugin.menu.cycleMenu();
            plugin.menu.showMenu(event.getPlayer());
        } else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            plugin.menu.doSelectedMenuItem(event.getPlayer());
        }
    }

    /**
     * Handles MapInitializeEvents for this plugin.
     *
     * @param event the MapInitializeEvent.
     */
    @EventHandler(event = MapInitializeEvent.class, priority = org.bukkit.event.EventPriority.NORMAL)
    public void onMapInitialize(MapInitializeEvent event) {
        for (MapRenderer renderer : event.getMap().getRenderers()) {
            System.out.println(renderer);

        }
        //event.getMap()
    }
}
