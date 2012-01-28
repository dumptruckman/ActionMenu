package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuHandle;
import com.dumptruckman.actionmenu2.api.view.MenuView;
import org.bukkit.command.CommandSender;

public class DefaultHandle implements MenuHandle {
    
    Menu menu;
    MenuView view;

    public DefaultHandle(Menu menu, MenuView view) {
        this.menu = menu;
        this.view = view;
    }

    @Override
    public void cycleMenu() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void cycleMenu(Boolean reverse) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Menu getMenu() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public MenuView getView() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setSender(CommandSender sender) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CommandSender getSender() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
