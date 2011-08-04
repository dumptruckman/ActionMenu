package com.dumptruckman.actionmenu.plugin;

import com.dumptruckman.actionmenu.ActionMenu;
import com.dumptruckman.actionmenu.map.MapActionMenu;
import com.dumptruckman.actionmenu.map.MapActionMenuItem;
import org.bukkit.entity.Fireball;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.logging.Logger;

/**
 * @author dumptruckman
 */
public class ActionMenuPlugin extends JavaPlugin {

    public ActionMenu menu;
    public static final Logger log = Logger.getLogger("Minecraft.ActionMenu");
    private ActionMenuPlayerListener playerListener = new ActionMenuPlayerListener(this);

    public void onEnable() {

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_ITEM_HELD, playerListener, Event.Priority.Normal, this);
        menu = new MapActionMenu(this).setWidth(50);
        menu.setHeader("Spells");
        menu.addMenuItem(new MapActionMenuItem("Fireball") {
            @Override
            public void run() {
                final Vector direction = getPlayer().getEyeLocation().getDirection().multiply(2);
                getPlayer().getWorld().spawn(getPlayer().getEyeLocation().add(direction.getX(), direction.getY(), direction.getZ()), Fireball.class);
            }
        });
        menu.addMenuItem(new MapActionMenuItem("This is a plugin of the emergency broadcast system.") {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        menu.addMenuItem(new MapActionMenuItem("12345678901234567890123456789012345678901234567890") {
            @Override
            public void run() {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        log.info(this.getDescription().getName() + " " + getDescription().getVersion() + " enabled.");
    }

    public void onDisable() {
        log.info(this.getDescription().getName() + " " + getDescription().getVersion() + " disabled.");
    }
}
