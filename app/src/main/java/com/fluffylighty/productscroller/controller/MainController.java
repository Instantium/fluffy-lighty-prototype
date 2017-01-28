package com.fluffylighty.productscroller.controller;

import android.util.Log;

import com.fluffylighty.productscroller.events.InitialisationEvent;
import com.fluffylighty.productscroller.events.PostListUpdatedEvent;
import com.fluffylighty.productscroller.events.ProductListUpdatedEvent;
import com.fluffylighty.productscroller.model.GetPostsResponse;
import com.fluffylighty.productscroller.model.GetProductsResponse;
import com.fluffylighty.productscroller.model.Post;
import com.fluffylighty.productscroller.model.Product;
import com.fluffylighty.productscroller.services.RestCallService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;

import static com.fluffylighty.productscroller.Utilities.Constants.CLOTHING_CATEGORY_ID;
import static com.fluffylighty.productscroller.Utilities.Constants.FASHION_CATEGORY_NAME;
import static com.fluffylighty.productscroller.Utilities.Constants.LAMP_CATEGORY_ID;
import static com.fluffylighty.productscroller.Utilities.Constants.LIFESTYLE_CATEGORY_NAME;
import static com.fluffylighty.productscroller.Utilities.Constants.POSTS_PAGE_ITEMS;
import static com.fluffylighty.productscroller.Utilities.Constants.PRODUCTS_PAGE_ITEMS;

/**
 * Created by Nico Adler on 28.01.17.
 */

public class MainController {

    private static final String LOG_TAG = MainController.class.getSimpleName();

    public MainController() {

        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new InitialisationEvent());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onMessageEvent(InitialisationEvent initialisationEvent) {

        Log.d(LOG_TAG, "Asynchronous initialisation started.");

        fetchProductsForCategoryId(CLOTHING_CATEGORY_ID);
        fetchProductsForCategoryId(LAMP_CATEGORY_ID);

        fetchPostsForCategoryName(LIFESTYLE_CATEGORY_NAME);
        fetchPostsForCategoryName(FASHION_CATEGORY_NAME);
    }

    private void fetchPostsForCategoryName(final String categoryName) {
        Call<GetPostsResponse> call = RestCallService.getAPIInterface().getPostsForCategory(categoryName, POSTS_PAGE_ITEMS);

        RestCallService.getProductsForCategoryId(call, new RestCallService.SuccessCallback<GetPostsResponse>() {
            @Override
            public void onSuccess(GetPostsResponse body) {

                Post[] posts = body.getPosts();

                EventBus.getDefault().postSticky(new PostListUpdatedEvent(categoryName, posts));
            }
        });
    }

    private void fetchProductsForCategoryId(final int categoryId) {
        Call<GetProductsResponse> call = RestCallService.getAPIInterface().getProductsForCategory(categoryId, PRODUCTS_PAGE_ITEMS);

        RestCallService.getProductsForCategoryId(call, new RestCallService.SuccessCallback<GetProductsResponse>() {
            @Override
            public void onSuccess(GetProductsResponse body) {

                Product[] products = body.getProducts();

                EventBus.getDefault().postSticky(new ProductListUpdatedEvent(categoryId, products));
            }
        });
    }
}
