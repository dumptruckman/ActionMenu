package com.dumptruckman.actionmenu2.api;

import org.bukkit.command.CommandSender;

public interface MenuInterface {
    
    void setSender(CommandSender sender);
    
    CommandSender getSender();
}
