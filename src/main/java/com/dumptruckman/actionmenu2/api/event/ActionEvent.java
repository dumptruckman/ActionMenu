package com.dumptruckman.actionmenu2.api.event;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuItem;
import org.bukkit.command.CommandSender;

import java.util.EventObject;

public class ActionEvent extends MenuEvent {
    
    private MenuItem menuItem;
    
    public ActionEvent(Menu source, CommandSender sender, MenuItem menuItem) {
        super(source, sender);
        this.menuItem = menuItem;
    }

    public MenuItem getMenuItem() {
        return this.menuItem;
    }
}
