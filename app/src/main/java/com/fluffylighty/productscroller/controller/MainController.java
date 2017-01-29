package com.fluffylighty.productscroller.controller;

import android.util.Log;

import com.fluffylighty.productscroller.Utilities.Constants;
import com.fluffylighty.productscroller.events.InitialisationEvent;
import com.fluffylighty.productscroller.events.PostListsUpdatedEvent;
import com.fluffylighty.productscroller.events.ProductListsUpdatedEvent;
import com.fluffylighty.productscroller.model.GetPostsResponse;
import com.fluffylighty.productscroller.model.GetProductsResponse;
import com.fluffylighty.productscroller.model.Post;
import com.fluffylighty.productscroller.model.Product;
import com.fluffylighty.productscroller.services.RestCallService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private List<Post> fashionPostList = new ArrayList<>();
    private List<Post> lifestylePostList = new ArrayList<>();
    private List<Product> clothingProductList = new ArrayList<>();
    private List<Product> lampProductList = new ArrayList<>();

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

    private void updatePostsLists(Post[] posts, String categoryName) {

        List<Post> listToAddProductsTo;
        if (categoryName.equals(Constants.FASHION_CATEGORY_NAME)) {

            listToAddProductsTo = this.fashionPostList;
        } else {

            listToAddProductsTo = this.lifestylePostList;
        }

        listToAddProductsTo.clear();
        listToAddProductsTo.addAll(Arrays.asList(posts));


        EventBus.getDefault().postSticky(new PostListsUpdatedEvent(fashionPostList, lifestylePostList));
    }

    private void updateProductsLists(Product[] products, int categoryId) {

        List<Product> listToAddProductsTo;
        if (categoryId == Constants.CLOTHING_CATEGORY_ID) {

            listToAddProductsTo = this.clothingProductList;
        } else {

            listToAddProductsTo = this.lampProductList;
        }

        listToAddProductsTo.clear();
        listToAddProductsTo.addAll(Arrays.asList(products));

        EventBus.getDefault().postSticky(new ProductListsUpdatedEvent(clothingProductList, lampProductList));
    }

    private void fetchProductsForCategoryId(final int categoryId) {
        Call<GetProductsResponse> call = RestCallService.getAPIInterface().getProductsForCategory(categoryId, PRODUCTS_PAGE_ITEMS);

        RestCallService.getProductsForCategoryId(call, new RestCallService.SuccessCallback<GetProductsResponse>() {
            @Override
            public void onSuccess(GetProductsResponse body) {

                updateProductsLists(body.getProducts(), categoryId);
            }
        });
    }

    private void fetchPostsForCategoryName(final String categoryName) {
        Call<GetPostsResponse> call = RestCallService.getAPIInterface().getPostsForCategory(categoryName, POSTS_PAGE_ITEMS);

        RestCallService.getProductsForCategoryId(call, new RestCallService.SuccessCallback<GetPostsResponse>() {
            @Override
            public void onSuccess(GetPostsResponse body) {

                updatePostsLists(body.getPosts(), categoryName);
            }
        });
    }
}
