package com.dumptruckman.actionmenu2.api.util;

/**
 * A simple interface for building immutable objects.
 * @param <T> The type of object that this builder will be responsible for building.
 */
public interface Builder<T> {

    /**
     * Creates an immutable object of the type T.
     *
     * @return A new instance of type T.
     */
    T build();
}
