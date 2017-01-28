package com.fluffylighty.productscroller.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.fluffylighty.productscroller.R;
import com.fluffylighty.productscroller.Utilities.Constants;
import com.fluffylighty.productscroller.events.PostListUpdatedEvent;
import com.fluffylighty.productscroller.events.ProductListUpdatedEvent;
import com.fluffylighty.productscroller.model.Post;
import com.fluffylighty.productscroller.model.Product;
import com.fluffylighty.productscroller.model.VerticalListItemWrapper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<VerticalListItemWrapper> verticalListItemWrapperList = new ArrayList<>();
    private VerticalListAdapter verticalAdapter;
    private List<Post> fashionPostList = new ArrayList<>();
    private List<Post> lifestylePostList = new ArrayList<>();
    private List<Product> clothingProductList = new ArrayList<>();
    private List<Product> lampProductList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView verticalListView = (ListView) findViewById(R.id.vertical_listview);

        verticalAdapter = new VerticalListAdapter(this, verticalListItemWrapperList);
        verticalListView.setAdapter(verticalAdapter);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ProductListUpdatedEvent productListUpdatedEvent) {

        List<Product> listToAddProductsTo;
        if (productListUpdatedEvent.getCategoryId() == Constants.CLOTHING_CATEGORY_ID) {

            listToAddProductsTo = this.clothingProductList;
        } else {

            listToAddProductsTo = this.lampProductList;
        }

        listToAddProductsTo.clear();
        listToAddProductsTo.addAll(Arrays.asList(productListUpdatedEvent.getProducts()));

        updateWrapperList();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PostListUpdatedEvent postListUpdatedEvent) {

        List<Post> listToAddPostsTo;
        if (postListUpdatedEvent.getCategoryName().equals(Constants.FASHION_CATEGORY_NAME)) {
            listToAddPostsTo = this.fashionPostList;
        } else {
            listToAddPostsTo = this.lifestylePostList;
        }

        listToAddPostsTo.clear();
        listToAddPostsTo.addAll(Arrays.asList(postListUpdatedEvent.getPosts()));

        updateWrapperList();
    }

    private void updateWrapperList() {

        verticalListItemWrapperList.clear();

        verticalListItemWrapperList.add(new VerticalListItemWrapper(clothingProductList));

        for (Post post : fashionPostList) {

            verticalListItemWrapperList.add(new VerticalListItemWrapper(post));
        }

        verticalListItemWrapperList.add(new VerticalListItemWrapper(lampProductList));

        for (Post post : lifestylePostList) {

            verticalListItemWrapperList.add(new VerticalListItemWrapper(post));
        }

        verticalAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }
}
