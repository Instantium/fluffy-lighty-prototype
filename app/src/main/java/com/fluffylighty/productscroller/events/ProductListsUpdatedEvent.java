package com.fluffylighty.productscroller.events;

import com.fluffylighty.productscroller.model.Product;

import java.util.List;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class ProductListsUpdatedEvent {

    private final List<Product> clothingProductList;
    private final List<Product> lampProductList;

    public ProductListsUpdatedEvent(List<Product> clothingProductList, List<Product> lampProductList) {
        this.clothingProductList = clothingProductList;
        this.lampProductList = lampProductList;
    }

    public List<Product> getClothingProductList() {
        return clothingProductList;
    }

    public List<Product> getLampProductList() {
        return lampProductList;
    }
}
