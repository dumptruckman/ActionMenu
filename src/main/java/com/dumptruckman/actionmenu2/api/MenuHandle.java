package com.dumptruckman.actionmenu2.api;

import com.dumptruckman.actionmenu2.api.view.MenuView;

public interface MenuHandle extends MenuInterface {

    void cycleMenu();

    void cycleMenu(Boolean reverse);

    Menu getMenu();
    
    MenuView getView();
}
