package com.fluffylighty.productscroller.model;

/**
 * Created by Nico Adler on 29.01.17.
 */
public class PostCategory {

    private final String name;
    private final String color;

    /**
     * Dummy constructor as this Object will only be instantiated via gson
     */
    public PostCategory() {
        name = "";
        color = "";
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
