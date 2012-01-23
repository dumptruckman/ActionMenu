package com.dumptruckman.actionmenu;

/**
 * Thrown when a menu item is not compatible with the type of menu being used.
 */
public class IncompatibleMenuItemException extends RuntimeException {

    public IncompatibleMenuItemException(String message) {
        super(message);
    }

    public IncompatibleMenuItemException() {
        this("");
    }
}
