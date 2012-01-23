package com.dumptruckman.actionmenu.map;

import com.dumptruckman.actionmenu.PlayerActionMenuItem;
import org.bukkit.map.MapFont;
import org.bukkit.map.MinecraftFont;

/**
 * @author dumptruckman
 */
public abstract class MapActionMenuItem extends PlayerActionMenuItem {

    protected MapFont font;
    protected int indent;
    protected int lineSpacing;

    public MapActionMenuItem(String text) {
        super(text);
        font = MinecraftFont.Font;
        indent = MapDefaults.LINE_INDENT;
        lineSpacing = 3;
    }

    public MapFont getFont() {
        return font;
    }

    public MapActionMenuItem setFont(MapFont font) {
        this.font = font;
        return this;
    }

    public int getIndent() {
        return indent;
    }

    public MapActionMenuItem setIndent(int pixels) {
        this.indent = pixels;
        return this;
    }

    public int getLineSpacing() {
        return lineSpacing;
    }

    public MapActionMenuItem setLineSpacing(int pixels) {
        this.lineSpacing = pixels;
        return this;
    }
}
