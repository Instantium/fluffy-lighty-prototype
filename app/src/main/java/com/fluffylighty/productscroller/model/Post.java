package com.fluffylighty.productscroller.model;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class Post {

    private final String title;
    private final String teaserImage;
    private final PostCategory category;

    /**
     * Dummy constructor as this Object will only be instantiated via gson
     */
    private Post() {
        title = "";
        teaserImage = "";
        category = new PostCategory();
    }

    public String getTitle() {
        return title;
    }

    public String getTeaserImage() {
        return teaserImage;
    }

    public PostCategory getCategory() {
        return category;
    }
}
