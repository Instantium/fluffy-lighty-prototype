package com.fluffylighty.productscroller.model;

/**
 * Created by Nico Adler on 28.01.17.
 */

public class GetPostsResponse {

    private final Post[] posts;

    /**
     * Dummy constructor as this Object will only be instantiated via gson
     */
    private GetPostsResponse() {
        this.posts = new Post[0];
    }

    public Post[] getPosts() {
        return posts;
    }
}
