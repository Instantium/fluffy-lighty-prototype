package com.fluffylighty.productscroller.events;

import com.fluffylighty.productscroller.model.Post;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class PostListUpdatedEvent {

    private final Post[] posts;
    private final String categoryName;

    public PostListUpdatedEvent(String categoryName, Post[] posts) {
        this.posts = posts;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Post[] getPosts() {
        return posts;
    }
}
