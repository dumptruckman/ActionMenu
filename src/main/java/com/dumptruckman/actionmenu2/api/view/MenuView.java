package com.dumptruckman.actionmenu2.api.view;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuInterface;
import org.bukkit.command.CommandSender;

public interface MenuView extends MenuInterface {

    void setMenu(Menu menu);
    
    Menu getMenu();
    
    void show(CommandSender sender);
}
