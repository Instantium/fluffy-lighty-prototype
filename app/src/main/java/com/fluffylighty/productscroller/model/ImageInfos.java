package com.fluffylighty.productscroller.model;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class ImageInfos {

    private final String url;
    private final boolean primary;

    /**
     * Dummy constructor as this Object will only be instantiated via gson
     */
    public ImageInfos() {
        this.url = "";
        this.primary = false;
    }

    public String getUrl() {
        return url;
    }

    public boolean isPrimary() {
        return primary;
    }
}
