package com.dumptruckman.actionmenu2;

public interface Menu {

    MenuContents<? extends MenuItem> getContents();
    
    MenuItem getSelectedMenuItem();
}
