package com.dumptruckman.actionmenu.map;

import com.dumptruckman.actionmenu.ActionMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.map.*;
import org.bukkit.plugin.java.JavaPlugin;
import sun.plugin2.main.server.Plugin;

/**
 * @author dumptruckman
 */
public class MapActionMenu extends ActionMenu {

    private MapFont mapFont = MinecraftFont.Font;

    private int x;
    private int y;
    private int width;
    private int height;
    private int lineSpacing;
    private int betweenCharSpace;
    private int scrollPos;
    private MapRenderer mapRenderer;
    private MapCanvas mapCanvas;

    public MapActionMenu(JavaPlugin plugin) {
        super(plugin);
        x = 2;
        y = 10;
        width = 120;
        height = 116;
        lineSpacing = 1;
        betweenCharSpace = 3;
        scrollPos = 0;
        mapRenderer = new MapActionMenuRenderer(this);
    }

    public MapActionMenu setFont(MapFont font) {
        this.mapFont = font;
        return this;
    }

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
    
    protected int writeLines(MapCanvas canvas, int x, int y, MapFont font, String text) {
        int xPos = x;
        int yPos = y;
        int xLimit = getWidth() - x;
        int yLimit = getHeight() - y;
        int spaceWidth = font.getWidth(" ") + betweenCharSpace;
        String[] words = text.split("\\s");
        String lineBuffer = "";
        int lineWidth = 0;
        for (int i = 0; i < words.length; i++) {
            int wordWidth = font.getWidth(words[i]);
            if (wordWidth <= xLimit) {
                if (xPos + lineWidth + wordWidth <= xLimit) {
                    lineBuffer += words[i] + " ";
                    lineWidth = font.getWidth(lineBuffer);
                } else {
                    canvas.drawText(xPos, yPos, font, lineBuffer);
                    lineBuffer = "";
                    lineWidth = 0;
                    yPos += font.getHeight() + getLineSpacing();
                    i--;
                    continue;
                }
            } else {
                char[] chars = words[i].toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    String sChar = Character.toString(chars[j]);
                    int charWidth = font.getWidth(sChar);
                    if (xPos + lineWidth + charWidth < xLimit) {
                        lineBuffer += sChar;
                        lineWidth = font.getWidth(lineBuffer);
                    } else {
                        canvas.drawText(xPos, yPos, font, lineBuffer);
                        lineBuffer = "";
                        lineWidth = 0;
                        yPos += font.getHeight() + getLineSpacing();
                        j--;
                        continue;
                    }
                }
                if (lineWidth != 0) {
                    lineBuffer += " ";
                    lineWidth = font.getWidth(lineBuffer);
                }
            }
        }
        if (lineWidth != 0) {
            canvas.drawText(xPos, yPos, font, lineBuffer);
            yPos += font.getHeight() + getLineSpacing();
        }
        return yPos;
    }

    public void showMenu(CommandSender sender) {
        showMenu((Player) sender, getPlugin().getServer().getMap(((Player) sender).getItemInHand().getDurability()));
    }

    public void showMenu(Player player, MapView mapView) {
        if (!mapView.isVirtual()) {
            mapView.getRenderers().remove(0);
        }
        if (!mapView.getRenderers().contains(getMapRenderer())) {
            mapView.addRenderer(getMapRenderer());
        }
        player.sendMap(mapView);
    }
}
