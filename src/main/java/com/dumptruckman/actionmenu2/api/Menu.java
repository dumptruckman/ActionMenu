package com.dumptruckman.actionmenu2.api;

import com.dumptruckman.actionmenu2.api.event.MenuListener;

import java.util.List;

public interface Menu extends MenuInterface {

    MenuContents<MenuItem> getContents();
    
    MenuItem getSelected();

    List<MenuListener> getListeners();
    
    void run(MenuItem item);
}
