package com.dumptruckman.actionmenu2.api.event;

import java.util.EventObject;

public class MenuContentsEvent extends EventObject {

    public final static int CONTENTS_CHANGED = 0;
    public final static int CONTENTS_ADDED = 1;
    public final static int CONTENTS_REMOVED = 2;
    public final static int SELECTION_CHANGED = 3;
    
    private int type;
    private int index0;
    private int index1;
    
    public MenuContentsEvent(Object source, int type, int index0, int index1) {
        super(source);
        this.type = type;
        this.index0 = index0;
        this.index1 = index1;
    }
    
    public int getType() {
        return this.type;
    }
    
    public int getIndex0() {
        return this.index0;
    }
    
    public int getIndex1() {
        return this.index1;
    }
}
