package com.fluffylighty.productscroller.model;

import java.util.List;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class VerticalListItemWrapper {

    private final WrapperType wrapperType;
    private int sectionHeaderTitleResId;
    private int sectionHeaderSubtitleResId;
    private Post post;
    private List<Product> products;

    public enum WrapperType {
        POST,
        PRODUCT_LIST,
        SECTION_HEADER
    }

    public VerticalListItemWrapper(Post post) {
        this.post = post;
        wrapperType = WrapperType.POST;
    }

    public VerticalListItemWrapper(List<Product> products) {
        this.products = products;
        wrapperType = WrapperType.PRODUCT_LIST;
    }

    public VerticalListItemWrapper(int sectionHeaderTitleResId, int sectionHeaderSubtitleResId) {

        this.sectionHeaderTitleResId = sectionHeaderTitleResId;
        this.sectionHeaderSubtitleResId = sectionHeaderSubtitleResId;
        wrapperType = WrapperType.SECTION_HEADER;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Post getPost() {
        return post;
    }

    public WrapperType getWrapperType() {
        return wrapperType;
    }

    public int getSectionHeaderTitleResId() {
        return sectionHeaderTitleResId;
    }

    public int getSectionHeaderSubtitleResId() {
        return sectionHeaderSubtitleResId;
    }
}
