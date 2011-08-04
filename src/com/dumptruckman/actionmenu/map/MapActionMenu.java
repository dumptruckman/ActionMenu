package com.dumptruckman.actionmenu.map;

import com.dumptruckman.actionmenu.ActionMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.map.*;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author dumptruckman
 */
public class MapActionMenu extends ActionMenu {

    private MapFont mapFont = MinecraftFont.Font;

    private int x;
    private int y;
    private int width;
    private int height;
    private int newLineSpace;
    private int betweenCharSpace;
    private int scrollPos;

    public MapActionMenu(JavaPlugin plugin) {
        super(plugin);
        x = 2;
        y = 10;
        width = 120;
        height = 116;
        newLineSpace = 1;
        betweenCharSpace = 3;
        scrollPos = 0;
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

    public MapActionMenu setNewLineSpace(int space) {
        this.newLineSpace = space;
        return this;
    }
    
    public int getNewLineSpace() {
        return newLineSpace;
    }

    public int getScrollPos() {
        return scrollPos;
    }

    public MapActionMenu setScrollPos(int newPos) {
        scrollPos = newPos;
        return this;
    }

    public void scrollMenu() {
        scrollMenu(false);
    }

    public void scrollMenu(boolean reverse) {
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
    }
    
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
                    yPos += font.getHeight() + getNewLineSpace();
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
                        yPos += font.getHeight() + getNewLineSpace();
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
            yPos += font.getHeight() + getNewLineSpace();
        }
        return yPos;
    }

    public void showMenu(CommandSender sender) {
        showMenu((Player)sender, ((Player)sender).getItemInHand().getDurability());
    }

    public void showMenu(Player player, short mapId) {
        MapView mapView = getPlugin().getServer().getMap(mapId);

        for(MapRenderer mapRenderer : mapView.getRenderers()) {
            mapView.removeRenderer(mapRenderer);
        }

        mapView.addRenderer(new MapRenderer() {
            @Override
            public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
                int y = getY();
                int scrollPos = getScrollPos();
                for (int i = scrollPos; i < getHeader().size(); i++) {
                    y = writeLines(mapCanvas, getX(), y, mapFont, getHeader().get(i));
                }
                scrollPos -= getHeader().size();
                if (scrollPos < 0) scrollPos = 0;
                for (int i = scrollPos; i < getContents().size(); i++) {
                    if (!(getContents().get(i) instanceof MapActionMenuItem)) continue;
                    MapActionMenuItem item = (MapActionMenuItem)getContents().get(i);
                    String text = "";
                    if (getMenuIndex() == i) {
                        text += "-> ";
                    }
                    text += item.getText();
                    y = writeLines(mapCanvas, getX(), y+2, item.getFont(), text);
                }
                scrollPos -= getContents().size();
                if (scrollPos < 0) scrollPos = 0;
                for (int i = scrollPos; i < getFooter().size(); i++) {
                    y = writeLines(mapCanvas, getX(), y+2, mapFont, getFooter().get(i));
                }
            }
        });
        
        player.sendMap(mapView);
    }
}
