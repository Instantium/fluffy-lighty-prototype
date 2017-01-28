package com.fluffylighty.productscroller.model;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class VerticalListItemWrapper {

    private final Product[] products;

    public VerticalListItemWrapper(Product[] products) {
        if (products != null) {
            this.products = products;
        } else {
            this.products = new Product[0];
        }
    }

    public Product[] getProducts() {
        return products;
    }
}
