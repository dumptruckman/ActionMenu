package com.dumptruckman.actionmenu.plugin;

import com.dumptruckman.actionmenu.ActionMenu;
import com.dumptruckman.actionmenu.map.MapActionMenu;
import com.dumptruckman.actionmenu.map.MapActionMenuItem;
import org.bukkit.entity.Fireball;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.logging.Logger;

/**
 * A plugin class for testing the ActionMenu API.
 */
public class ActionMenuPluginOld extends JavaPlugin {

    public ActionMenu menu;

    /**
     * The log for this plugin.
     */
    public static final Logger LOG = Logger.getLogger("Minecraft.ActionMenu");

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ActionMenuListener(this), this);
        //pm.registerEvent(Event.Type.PLAYER_ITEM_HELD, listener, Event.Priority.Normal, this);
        menu = new MapActionMenu();
        menu.setHeader("Spells");
        menu.add(new MapActionMenuItem("Fireball") {
            public void run() {
                final Vector direction = getPlayer().getEyeLocation().getDirection().multiply(2);
                getPlayer().getWorld().spawn(getPlayer().getEyeLocation().add(direction.getX(), direction.getY(),
                        direction.getZ()), Fireball.class);
            }
        });
        menu.add(new MapActionMenuItem("This is a plugin of the emergency broadcast system.") {
            public void run() {
            }
        });
        menu.add(new MapActionMenuItem("12345678901234567890123456789012345678901234567890") {
            public void run() {
            }
        });
        LOG.info(this + " enabled.");
    }

    @Override
    public void onDisable() {
        LOG.info(this + " disabled.");
    }

    /**
     * @return The Menu object for this test plugin.
     */
    public ActionMenu getMenu() {
        return this.menu;
    }
}
