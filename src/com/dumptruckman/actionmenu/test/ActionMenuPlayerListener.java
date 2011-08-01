package com.dumptruckman.actionmenu.test;

import com.dumptruckman.actionmenu.ActionMenuPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkitcontrib.gui.*;
import org.bukkitcontrib.player.ContribPlayer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
    }

    public void onPlayerInteract(PlayerInteractEvent event) {
        Widget texture = new GenericTexture(
                "http://www.webtextures.net/textures/simple_gray_patchwork_texture/simple_gray_patchwork_texture.jpg")
                .setX(0).setY(0).setWidth(50).setHeight(50).setPriority(RenderPriority.Lowest);

        
        ((ContribPlayer) event.getPlayer()).getMainScreen().attachWidget(texture);
    }
}
