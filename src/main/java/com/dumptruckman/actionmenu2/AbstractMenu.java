package com.dumptruckman.actionmenu2;

import java.util.ArrayList;

public abstract class AbstractMenu implements Menu {
    
    MenuContents<MenuItem> contents = new DefaultMenuContents<MenuItem>(new ArrayList<MenuItem>());
    
    public MenuContents<? extends MenuItem> getContents() {
        return this.contents;
    }
    
    public MenuItem getSelectedMenuItem() {
        return this.getContents().get(this.getContents().getSelectedIndex());
    }
}
