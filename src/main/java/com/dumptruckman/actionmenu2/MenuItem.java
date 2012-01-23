package com.dumptruckman.actionmenu2;

import com.dumptruckman.actionmenu2.event.ActionListener;

public interface MenuItem {

    boolean addActionListener(ActionListener listener);
    
    boolean removeActionListener(ActionListener listener);
    
    public void doAction();
}
