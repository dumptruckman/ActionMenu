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
        /*ItemStack item = event.getPlayer().getItemInHand();
        if (item.getType() != Material.MAP) return;

        MapView map = plugin.getServer().getMap(item.getDurability());
        map.getRenderers().clear();
        map.getRenderers().add(new MapRenderer() {
            @Override
            public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
                mapCanvas.
            }
        });*/
        if (event.getPlayer().getItemInHand().getType() != Material.MAP) return;
        plugin.menu.showMenu(event.getPlayer());
    }

    public void onPlayerInteract(PlayerInteractEvent event) {
        //Widget texture = new GenericTexture(
        //        "http://www.webtextures.net/textures/simple_gray_patchwork_texture/simple_gray_patchwork_texture.jpg")
        //        .setX(0).setY(0).setWidth(50).setHeight(50).setPriority(RenderPriority.Lowest)
        
        //((ContribPlayer) event.getPlayer()).getMainScreen().attachWidget(texture);

        if (event.getPlayer().getItemInHand().getType() != Material.MAP) return;
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            plugin.menu.cycleMenu();
            plugin.menu.showMenu(event.getPlayer());
        } else if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            plugin.menu.doSelectedMenuItem(event.getPlayer());
        }
    }
}
