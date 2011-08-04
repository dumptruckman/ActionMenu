package com.dumptruckman.actionmenu.map;

import com.dumptruckman.actionmenu.ActionMenu;
import com.sun.javaws.jnl.XMLFormat;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.map.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * @author dumptruckman
 */
public class MapActionMenu extends ActionMenu {

    private MapFont mapFont = MinecraftFont.Font;

    private int x = 2;
    private int y = 10;
    private int width = 124;
    private int height = 116;
    private int newLineSpace = 1;
    private int betweenCharSpace = 3;

    public MapActionMenu(JavaPlugin plugin) {
        super(plugin);
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

    protected int writeLine(MapCanvas canvas, int x, int y, MapFont font, String text) {
        int xPos = x;
        int yPos = y;
        int xLimit = getWidth() - x;
        int yLimit = getHeight() - y;
        int spaceWidth = font.getWidth(" ") + betweenCharSpace;
        String[] words = text.split("\\s");
        for (int i = 0; i < words.length; i++) {
            // If our yPos has gone to far, quit writing.
            if (yPos > yLimit) return yPos;
            // If a word is too long for one line, write it and wrap the text.
            int wordWidth = font.getWidth(words[i]);
            if (wordWidth > xLimit) {
                System.out.println("big word");
                char[] chars = words[i].toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    // If our yPos has gone to far, quit writing.
                    if (yPos > yLimit) return yPos;
                    int charWidth = font.getWidth(Character.toString(chars[i]));
                    if (charWidth + xPos > xLimit) {
                        xPos = x;
                        yPos += font.getHeight() + getNewLineSpace();
                    }
                    canvas.drawText(xPos, yPos, font, Character.toString(chars[i]));
                    xPos += charWidth + betweenCharSpace;
                }
                canvas.drawText(xPos, yPos, font, " ");
                xPos += spaceWidth;
                continue;
            }
            // If a word will be truncated, start it on a new line
            if (wordWidth > xLimit - xPos) {
                System.out.println("line end");
                yPos += font.getHeight() + getNewLineSpace();
                xPos = x;
                if(yPos > yLimit) return yPos;
            }
            System.out.println("x:" + xPos + " y:" + yPos + " width:" + wordWidth + " " + words[i]);
            canvas.drawText(xPos, yPos, font, words[i] + " ");
            xPos += wordWidth + spaceWidth;
        }
        yPos += font.getHeight() + getNewLineSpace();
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
                for (String header : getHeader()) {
                    y = writeLine(mapCanvas, getX(), y, mapFont, header);
                }
                for (int i = 0; i < getContents().size(); i++) {
                    if (!(getContents().get(i) instanceof MapActionMenuItem)) continue;
                    MapActionMenuItem item = (MapActionMenuItem)getContents().get(i);
                    String text = "";
                    if (getMenuIndex() == i) {
                        text += "-> ";
                    }
                    text += item.getText();
                    MapFont font = item.getFont();
                    if (font == null) font = mapFont;
                    y = writeLine(mapCanvas, getX(), y, mapFont, text);
                }
                for (String footer : getFooter()) {
                    y = writeLine(mapCanvas, getX(), y, mapFont, footer);
                }
            }
        });
        
        player.sendMap(mapView);
    }
}
