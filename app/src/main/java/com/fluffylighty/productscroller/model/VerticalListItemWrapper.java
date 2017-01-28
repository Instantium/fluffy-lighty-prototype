package com.fluffylighty.productscroller.model;

import java.util.List;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class VerticalListItemWrapper {

    private final boolean isPost;
    private Post post;
    private List<Product> products;

    public VerticalListItemWrapper(Post post) {
        this.post = post;
        isPost = true;
    }

    public VerticalListItemWrapper(List<Product> products) {
        this.products = products;
        isPost = false;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Post getPost() {
        return post;
    }

    public boolean isPost() {
        return isPost;
    }
}
