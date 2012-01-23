package com.dumptruckman.actionmenu2.event;

public interface MenuContentsListener {

    void onContentsAdd(MenuContentsEvent event);

    void onContentsRemove(MenuContentsEvent event);

    void onContentsChange(MenuContentsEvent event);
    
    void onSelectionChange(MenuContentsEvent event);
}
