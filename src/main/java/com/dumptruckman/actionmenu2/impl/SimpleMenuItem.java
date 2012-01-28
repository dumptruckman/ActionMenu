package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.event.ActionListener;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleMenuItem implements MenuItem {

    private final List<ActionListener> listeners = new ArrayList<ActionListener>();
    private List<String> text = null;
    private Image image = null;

    @Override
    public List<ActionListener> getListeners() {
        return this.listeners;
    }

    @Override
    public void setText(String... text) {
        this.text = Arrays.asList(text);
    }

    @Override
    public void setLine(int index, String line) {
        this.text.set(index, line);
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String getText() {
        if (this.text == null) {
            return null;
        }
        if (this.text.isEmpty()) {
            return "";
        }
        return this.text.get(0);
    }

    @Override
    public String getFullText() {
        if (this.text == null) {
            return null;
        }
        if (this.text.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String line : this.text) {
            if (!builder.toString().isEmpty()) {
                builder.append(System.getProperty("line.separator"));
            }
            builder.append(line);
        }
        return builder.toString();
    }

    @Override
    public List<String> getLines() {
        return this.text;
    }

    @Override
    public Image getImage() {
        return this.image;
    }
}
