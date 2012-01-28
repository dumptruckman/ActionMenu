package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.MenuContents;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.event.MenuContentsEvent;
import com.dumptruckman.actionmenu2.api.event.MenuContentsListener;
import com.dumptruckman.actionmenu2.api.util.ForwardingList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultContents<E extends MenuItem> extends ForwardingList<E> implements MenuContents<E> {
    
    private final List<MenuContentsListener> listeners;
    private int selectedIndex;
    
    public DefaultContents(List<E> contents) {
        super(contents);
        this.listeners = new ArrayList<MenuContentsListener>();
        this.selectedIndex = this.size() - 1;
    }
    
    public DefaultContents() {
        this(new ArrayList<E>());
    }

    @Override
    public boolean addMenuContentsListener(MenuContentsListener listener) {
        return this.listeners.add(listener);
    }

    @Override
    public boolean removeMenuContentsListener(MenuContentsListener listener) {
        return this.listeners.remove(listener);
    }

    @Override
    public int getSelectedIndex() {
        return selectedIndex;
    }
    
    @Override
    public void setSelectedIndex(int index) {
        if (index < -1 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        int oldIndex = this.selectedIndex;
        this.selectedIndex = index;
        for (MenuContentsListener listener : this.listeners) {
            listener.onSelectionChange(new MenuContentsEvent(this, MenuContentsEvent.SELECTION_CHANGED,
                    oldIndex, index));
        }
    }
    
    @Override
    public boolean add(E e) {
        int index = this.size();
        boolean added = super.add(e);
        if (added) {
            for (MenuContentsListener listener : this.listeners) {
                listener.onContentsAdd(new MenuContentsEvent(this, MenuContentsEvent.CONTENTS_ADDED,
                        index, index));
            }
            if (this.getSelectedIndex() == -1) {
                this.setSelectedIndex(0);
            }
        }
        return added;
    }
    
    @Override
    public void add(int index, E e) {
        super.add(index, e);
        for (MenuContentsListener listener : this.listeners) {
            listener.onContentsAdd(new MenuContentsEvent(this, MenuContentsEvent.CONTENTS_ADDED,
                    index, index));
        }
        if (this.getSelectedIndex() == -1) {
            this.setSelectedIndex(0);
        } else if (this.getSelectedIndex() >= index) {
            this.setSelectedIndex(this.getSelectedIndex() + 1);
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = this.indexOf(o);
        boolean removed = super.remove(o);
        if (removed) {
            for (MenuContentsListener listener : this.listeners) {
                listener.onContentsRemove(new MenuContentsEvent(this, MenuContentsEvent.CONTENTS_REMOVED,
                        index, index));
            }
            if (this.getSelectedIndex() >= this.size()) {
                this.setSelectedIndex(this.size() - 1);
            }
        }
        return removed;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        int index0 = this.size();
        boolean added = super.addAll(c);
        int index1 = this.size();
        if (added) {
            for (MenuContentsListener listener : this.listeners) {
                listener.onContentsAdd(new MenuContentsEvent(this, MenuContentsEvent.CONTENTS_ADDED,
                        index0, index1));
            }
            if (this.getSelectedIndex() == -1) {
                this.setSelectedIndex(0);
            }
        }
        return added;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        boolean added = super.addAll(index, c);
        int index1 = index + c.size();
        if (added) {
            for (MenuContentsListener listener : this.listeners) {
                listener.onContentsAdd(new MenuContentsEvent(this, MenuContentsEvent.CONTENTS_ADDED,
                        index, index1));
            }
            if (this.getSelectedIndex() == -1) {
                this.setSelectedIndex(0);
            } else if (this.getSelectedIndex() >= index) {
                this.setSelectedIndex(this.getSelectedIndex() + c.size());
            }
        }
        return added;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        E newSelectedItem = null;
        for (int i = this.getSelectedIndex(); i >= 0; i--) {
            if (!c.contains(this.get(i))) {
                newSelectedItem = this.get(i);
                break;
            }
        }
        boolean removed = super.removeAll(c);
        if (removed) {
            for (MenuContentsListener listener : this.listeners) {
                listener.onContentsChange(new MenuContentsEvent(this, MenuContentsEvent.CONTENTS_CHANGED,
                        -1, -1));
            }
            if (newSelectedItem == null) {
                if (this.size() > 0) {
                    this.setSelectedIndex(0);
                } else {
                    this.setSelectedIndex(-1);
                }
            } else {
                this.setSelectedIndex(this.indexOf(newSelectedItem));
            }
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        E newSelectedItem = null;
        for (int i = this.getSelectedIndex(); i >= 0; i--) {
            if (c.contains(this.get(i))) {
                newSelectedItem = this.get(i);
                break;
            }
        }
        boolean removed = super.retainAll(c);
        if (removed) {
            for (MenuContentsListener listener : this.listeners) {
                listener.onContentsChange(new MenuContentsEvent(this, MenuContentsEvent.CONTENTS_CHANGED,
                        -1, -1));
            }
            if (newSelectedItem == null) {
                if (this.size() > 0) {
                    this.setSelectedIndex(0);
                } else {
                    this.setSelectedIndex(-1);
                }
            } else {
                this.setSelectedIndex(this.indexOf(newSelectedItem));
            }
        }
        return removed;
    }

    @Override
    public void clear() {
        int index1 = this.size();
        super.clear();
        for (MenuContentsListener listener : this.listeners) {
            listener.onContentsChange(new MenuContentsEvent(this, MenuContentsEvent.CONTENTS_REMOVED,
                    0, index1));
        }
        this.setSelectedIndex(-1);
    }

    @Override
    public E set(int index, E element) {
        E e = super.set(index, element);
        for (MenuContentsListener listener : this.listeners) {
            listener.onContentsChange(new MenuContentsEvent(this, MenuContentsEvent.CONTENTS_CHANGED,
                    index, index));
        }
        return e;
    }

    @Override
    public E remove(int index) {
        E e = super.remove(index);
        for (MenuContentsListener listener : this.listeners) {
            listener.onContentsChange(new MenuContentsEvent(this, MenuContentsEvent.CONTENTS_REMOVED,
                    index, index));
        }
        if (this.getSelectedIndex() == index) {
            if (this.getSelectedIndex() - 1 >= 0) {
                this.setSelectedIndex(this.getSelectedIndex() - 1);
            } else if (this.getSelectedIndex() >= this.size()) {
                this.setSelectedIndex(this.size() - 1);
            }
        }
        return e;
    }
}
