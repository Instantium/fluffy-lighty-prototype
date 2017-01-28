package com.fluffylighty.productscroller;

import android.support.test.runner.AndroidJUnit4;

import com.fluffylighty.productscroller.model.GetPostsResponse;
import com.fluffylighty.productscroller.model.GetProductsResponse;
import com.fluffylighty.productscroller.model.Post;
import com.fluffylighty.productscroller.model.Product;
import com.fluffylighty.productscroller.services.RestCallService;

import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit2.Call;
import retrofit2.Response;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests the backendintegration
 */
@RunWith(AndroidJUnit4.class)
public class RestCallServiceTest {

    private static final int PAGE_ITEMS = 8;
    private static final String FASHION_CATEGORY_NAME = "fashion";
    private static final String LIFESTYLE_CATEGORY_NAME = "lifestyle";
    private static final int LAMP_CATEGORY_ID = 28107;
    private static final int CLOTHING_CATEGORY_ID = 10203;

    @Test
    public void getFashionPostsTest() throws Exception {

        getPostsTest(FASHION_CATEGORY_NAME);
    }

    @Test
    public void getLifestylePostsTest() throws Exception {

        getPostsTest(LIFESTYLE_CATEGORY_NAME);
    }

    @Test
    public void getLampProductsTest() throws Exception {

        getProductsTest(LAMP_CATEGORY_ID);
    }

    @Test
    public void getClothingProductsTest() throws Exception {

        getProductsTest(CLOTHING_CATEGORY_ID);
    }

    private void getProductsTest(int categoryId) throws java.io.IOException {
        Call<GetProductsResponse> call = RestCallService.getAPIInterface().getProductsForCategory(categoryId, PAGE_ITEMS);

        Response<GetProductsResponse> response = call.execute();

        assertNotNull(response);
        assertTrue(response.code() == HTTP_OK);

        GetProductsResponse body = response.body();

        assertNotNull(body);

        Product[] products = body.getProducts();
        assertNotNull(products);

        assertTrue(products.length == PAGE_ITEMS);
    }

    private void getPostsTest(String categoryName) throws java.io.IOException {
        Call<GetPostsResponse> call = RestCallService.getAPIInterface().getPostsForCategory(categoryName, PAGE_ITEMS);

        Response<GetPostsResponse> response = call.execute();

        assertNotNull(response);
        assertTrue(response.code() == HTTP_OK);

        GetPostsResponse body = response.body();

        assertNotNull(body);

        Post[] posts = body.getPosts();
        assertNotNull(posts);

        assertTrue(posts.length == PAGE_ITEMS);
    }
}
