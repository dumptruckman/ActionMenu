package com.dumptruckman.actionmenu.map;

import com.dumptruckman.actionmenu.ActionMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.map.MapFont;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;

/**
 * An implementation of ActionMenu for Minecraft maps.
 */
public class MapActionMenu extends ActionMenu {

    private MapFont mapFont = MinecraftFont.Font;

    private int x;
    private int y;
    private int width;
    private int height;
    private int lineSpacing;
    private int scrollPos;
    private MapRenderer mapRenderer;
    private boolean changed;

    public MapActionMenu() {
        x = MapDefaults.X_POS;
        y = MapDefaults.Y_POS;
        width = MapDefaults.WIDTH;
        height = MapDefaults.HEIGHT;
        lineSpacing = MapDefaults.LINE_SPACING;
        scrollPos = MapDefaults.SCROLL_POS;
        mapRenderer = new MapActionMenuRenderer(this);
        changed = true;
    }

    /**
     * Sets the font for this MapActionMenu.
     *
     * @param font the Font to be used.
     * @return This map for chaining.
     */
    public MapActionMenu setFont(MapFont font) {
        this.mapFont = font;
        return this;
    }

    /**
     * @return The Font being used by this map.
     */
    public MapFont getFont() {
        return mapFont;
    }

    public MapActionMenu setX(int x) {
        this.x = x;
        return this;
    }

    public MapActionMenu setY(int y) {
        this.y = y;
        return this;
    }

    public MapActionMenu setWidth(int width) {
        this.width = width;
        return this;
    }

    public MapActionMenu setHeight(int height) {
        this.height = height;
        return this;
    }

    public MapActionMenu setBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        return this;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public MapActionMenu setLineSpacing(int space) {
        this.lineSpacing = space;
        return this;
    }

    public int getLineSpacing() {
        return lineSpacing;
    }

    protected MapActionMenu setMapRenderer(MapRenderer mapRenderer) {
        this.mapRenderer = mapRenderer;
        return this;
    }

    public MapRenderer getMapRenderer() {
        return mapRenderer;
    }

    //public int getScrollPos() {
    //    return scrollPos;
    //}

    //public MapActionMenu setScrollPos(int newPos) {
    //    scrollPos = newPos;
    //    return this;
    //}

    //public void scrollMenu() {
    //    scrollMenu(false);
    //}

    /*public void scrollMenu(boolean reverse) {
        if (reverse) {
            scrollPos--;
        } else {
            scrollPos++;
        }
        int menuSize = getHeader().size() + getContents().size() + getFooter().size();
        if (scrollPos < 0) {
            scrollPos = menuSize - 1;
        }
        if (scrollPos >= menuSize) {
            scrollPos = 0;
        }
    }*/

    protected void onChange() {
        setChanged(true);
    }

    public void setChanged(boolean changes) {
        this.changed = changes;
    }

    public boolean hasChanged() {
        return changed;
    }

    public void showMenu(CommandSender sender) {
        showMenu((Player) sender, Bukkit.getServer().getMap(((Player) sender).getItemInHand().getDurability()));
    }

    public void showMenu(Player player, MapView mapView) {
        if (!mapView.isVirtual()) {
            mapView.removeRenderer(mapView.getRenderers().get(0));
        }
        if (mapView.getRenderers().contains(getMapRenderer())) {
            mapView.removeRenderer(getMapRenderer());
        }
        mapView.addRenderer(getMapRenderer());
        player.sendMap(mapView);
    }
}
