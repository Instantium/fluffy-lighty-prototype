package com.fluffylighty.productscroller.interfaces;

import com.fluffylighty.productscroller.model.GetProductsResponse;
import com.fluffylighty.productscroller.model.GetPostsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nico Adler on 28.01.17.
 */
public interface ApiInterface {

    @GET("posts")
    Call<GetPostsResponse> getPostsForCategory(@Query("category") String categoryName, @Query("pageItems") int pageItems);

    @GET("products")
    Call<GetProductsResponse> getProductsForCategory(@Query("category") int categoryID, @Query("pageItems") int pageItems);
}
