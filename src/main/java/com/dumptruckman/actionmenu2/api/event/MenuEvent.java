package com.dumptruckman.actionmenu2.api.event;

import com.dumptruckman.actionmenu2.api.Menu;
import org.bukkit.command.CommandSender;

public abstract class MenuEvent {
    
    private Menu source;
    private CommandSender sender;

    public MenuEvent(Menu source, CommandSender sender) {
        this.source = source;
        this.sender = sender;
    }

    public Menu getSource() {
        return this.source;
    }

    public CommandSender getSender() {
        return this.sender;
    }
}
