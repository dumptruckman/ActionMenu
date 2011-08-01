package com.dumptruckman.actionmenu.test;

import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.event.server.ServerListener;
import org.bukkit.map.MapRenderer;

import java.util.Map;

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
