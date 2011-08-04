package com.dumptruckman.actionmenu.map;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

/**
 * @author dumptruckman
 */
public class MapActionMenuRenderer extends MapRenderer {

    private MapActionMenu menu;

    public MapActionMenuRenderer(MapActionMenu menu) {
        this.menu = menu;
    }

    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
        int y = menu.getY();
        int scrollPos = 0;//getScrollPos();
        for (int i = scrollPos; i < menu.getHeader().size(); i++) {
            y = menu.writeLines(mapCanvas, menu.getX(), y, menu.getFont(), menu.getHeader().get(i));
        }
        scrollPos -= menu.getHeader().size();
        if (scrollPos < 0) scrollPos = 0;
        for (int i = scrollPos; i < menu.getContents().size(); i++) {
            if (!(menu.getContents().get(i) instanceof MapActionMenuItem)) continue;
            MapActionMenuItem item = (MapActionMenuItem)menu.getContents().get(i);
            String text = "";
            if (menu.getMenuIndex() == i) {
                text += "-> ";
            }
            text += item.getText();
            y = menu.writeLines(mapCanvas, menu.getX(), y+2, item.getFont(), text);
        }

        scrollPos -= menu.getContents().size();
        if (scrollPos < 0) scrollPos = 0;
        for (int i = scrollPos; i < menu.getFooter().size(); i++) {
            y = menu.writeLines(mapCanvas, menu.getX(), y+2, menu.getFont(), menu.getFooter().get(i));
        }
    }
}
