package com.dumptruckman.actionmenu2.api;

import com.dumptruckman.actionmenu2.api.view.MenuView;
import com.dumptruckman.actionmenu2.impl.DefaultContents;
import com.dumptruckman.actionmenu2.impl.DefaultHandle;
import com.dumptruckman.actionmenu2.impl.DefaultMenu;
import com.dumptruckman.actionmenu2.impl.SimpleSignView;
import org.bukkit.block.Sign;

import java.util.ArrayList;

public class Menus {

    private Menus() { }
    
    public static MenuHandle getMenuHandle(Sign sign) {
        MenuHandle menuHandle = new DefaultHandle(
                new DefaultMenu(new DefaultContents<MenuItem>(new ArrayList<MenuItem>())), 
                new SimpleSignView(sign));
        return menuHandle;
    }
}
