package com.fluffylighty.productscroller.model;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class Product {

    private final Currency currency;
    private final String name;
    private final float price;
    private final ImageInfo[] images;

    /**
     * Dummy constructor as this Object will only be instantiated via gson
     */
    public Product() {
        currency = new Currency();
        name = "";
        price = 0;
        images = new ImageInfo[0];
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public ImageInfo[] getImages() {
        return images;
    }
}
