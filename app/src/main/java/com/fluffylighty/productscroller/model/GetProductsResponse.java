package com.fluffylighty.productscroller.model;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class GetProductsResponse {

    private final Product[] products;

    /**
     * Dummy constructor as this Object will only be instantiated via gson
     */
    public GetProductsResponse() {
        products = new Product[0];
    }

    public Product[] getProducts() {
        return products;
    }
}
