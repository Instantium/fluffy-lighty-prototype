package com.fluffylighty.productscroller.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.fluffylighty.productscroller.R;
import com.fluffylighty.productscroller.events.ProductListUpdatedEvent;
import com.fluffylighty.productscroller.model.Product;
import com.fluffylighty.productscroller.model.VerticalListItemWrapper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<VerticalListItemWrapper> verticalListItemWrapperList = new ArrayList<>();
    private VerticalListAdapter verticalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView verticalListView = (ListView) findViewById(R.id.vertical_listview);

        verticalAdapter = new VerticalListAdapter(this, verticalListItemWrapperList);
        verticalListView.setAdapter(verticalAdapter);
    }

    private String[] createDummyData() {
        String[] result = new String[1000];

        for (int i = 0; i < result.length; i++) {
            result[i] = "Item" + i;
        }

        return result;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ProductListUpdatedEvent productListUpdatedEvent) {

        updateWrapperList(productListUpdatedEvent.getProducts());
    }

    private void updateWrapperList(Product[] products) {

        verticalListItemWrapperList.clear();

        for (int i = 0; i < 10; i++) {

            verticalListItemWrapperList.add(new VerticalListItemWrapper(products));
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
