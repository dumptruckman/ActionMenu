package com.dumptruckman.actionmenu.map;

import com.dumptruckman.actionmenu.PlayerActionMenuItem;
import org.bukkit.map.MapFont;
import org.bukkit.map.MinecraftFont;

/**
 * @author dumptruckman
 */
public abstract class MapActionMenuItem extends PlayerActionMenuItem {

    MapFont font;

    public MapActionMenuItem(String text) {
        super(text);
        font = MinecraftFont.Font;
    }

    public MapFont getFont() {
        return font;
    }

    public MapActionMenuItem setFont(MapFont font) {
        this.font = font;
        return this;
    }
}
