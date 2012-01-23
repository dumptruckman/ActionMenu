package com.dumptruckman.actionmenu2;

import com.dumptruckman.actionmenu2.event.ActionListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMenuItem implements MenuItem {

    private final List<ActionListener> listeners;

    public AbstractMenuItem() {
        this.listeners = new ArrayList<ActionListener>();
    }

    @Override
    public boolean addActionListener(ActionListener listener) {
        return this.listeners.add(listener);
    }

    @Override
    public boolean removeActionListener(ActionListener listener) {
        return this.listeners.remove(listener);
    }
}
