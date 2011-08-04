package com.dumptruckman.actionmenu.plugin;

import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.map.MapRenderer;

/**
 * @author dumptruckman
 */
public class ActionMenuServerListener extends ServerListener {

    public void onMapInitialize(MapInitializeEvent event) {
        for (MapRenderer renderer : event.getMap().getRenderers()) {
            System.out.println(renderer);
            
        }
        //event.getMap()
    }
}
