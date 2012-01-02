package com.dumptruckman.actionmenu;

public class IncompatibleMenuItemException extends RuntimeException {
    
    public IncompatibleMenuItemException(String message) {
        super(message);
    }

    public IncompatibleMenuItemException() {
        this("");
    }
}
