package com.fluffylighty.productscroller.events;

import com.fluffylighty.productscroller.model.Product;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class ProductListUpdatedEvent {

    private final int categoryId;
    private final Product[] products;

    public ProductListUpdatedEvent(int categoryId, Product[] products) {
        this.categoryId = categoryId;
        this.products = products;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public Product[] getProducts() {
        return products;
    }
}
