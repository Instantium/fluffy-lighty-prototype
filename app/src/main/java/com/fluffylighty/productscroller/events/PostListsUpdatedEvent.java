package com.fluffylighty.productscroller.events;

import com.fluffylighty.productscroller.model.Post;

import java.util.List;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class PostListsUpdatedEvent {

    private final List<Post> fashionPostList;
    private final List<Post> lifestylePostList;

    public PostListsUpdatedEvent(List<Post> fashionPostList, List<Post> lifestylePostList) {
        this.fashionPostList = fashionPostList;
        this.lifestylePostList = lifestylePostList;
    }

    public List<Post> getFashionPostList() {
        return fashionPostList;
    }

    public List<Post> getLifestylePostList() {
        return lifestylePostList;
    }
}
