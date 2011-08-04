package com.dumptruckman.actionmenu.map;

import com.dumptruckman.actionmenu.PlayerActionMenuItem;
import org.bukkit.map.MapFont;

/**
 * @author dumptruckman
 */
public abstract class MapActionMenuItem extends PlayerActionMenuItem {

    MapFont font = null;

    public MapActionMenuItem(String text) {
        super(text);
    }

    public MapFont getFont() {
        return font;
    }

    public MapActionMenuItem setFont(MapFont font) {
        this.font = font;
        return this;
    }
}
