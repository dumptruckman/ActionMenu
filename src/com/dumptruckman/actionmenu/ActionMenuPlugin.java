package com.dumptruckman.actionmenu;

import com.dumptruckman.actionmenu.test.ActionMenuPlayerListener;
import com.dumptruckman.actionmenu.test.ActionMenuServerListener;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * @author dumptruckman
 */
public class ActionMenuPlugin extends JavaPlugin {

    public static final Logger log = Logger.getLogger("Minecraft.ActionMenu");

    public void onEnable() {

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvent(Event.Type.PLAYER_INTERACT, new ActionMenuPlayerListener(this), Event.Priority.Normal, this);
        //pm.registerEvent(Event.Type.MAP_INITIALIZE, new ActionMenuServerListener(), Event.Priority.Normal, this);
        log.info(this.getDescription().getName() + " " + getDescription().getVersion() + " enabled.");
    }

    public void onDisable() {
        log.info(this.getDescription().getName() + " " + getDescription().getVersion() + " disabled.");
    }
}
