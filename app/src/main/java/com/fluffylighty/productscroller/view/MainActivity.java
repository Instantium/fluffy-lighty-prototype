package com.fluffylighty.productscroller.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.fluffylighty.productscroller.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView verticalListView = (ListView) findViewById(R.id.vertical_listview);

        ListAdapter verticalAdapter = new VerticalListAdapter(this, createDummyData());
        verticalListView.setAdapter(verticalAdapter);
    }

    private String[] createDummyData() {
        String[] result = new String[1000];

        for (int i = 0; i < result.length; i++) {
            result[i] = "Item" + i;
        }

        return result;
    }
}
