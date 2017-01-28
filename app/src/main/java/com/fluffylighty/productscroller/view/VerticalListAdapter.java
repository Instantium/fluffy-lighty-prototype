package com.fluffylighty.productscroller.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.fluffylighty.productscroller.R;
import com.fluffylighty.productscroller.model.Product;
import com.fluffylighty.productscroller.model.VerticalListItemWrapper;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class VerticalListAdapter extends ArrayAdapter<VerticalListItemWrapper> {

    public VerticalListAdapter(Context context, List<VerticalListItemWrapper> verticalListItemWrappers) {
        super(context, -1, verticalListItemWrappers);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vertical_listview_horizontal_listview_item, null);

            holder = new ViewHolder();
            TwoWayView horizontalListView = (TwoWayView) convertView.findViewById(R.id.horizontal_listview);
            holder.productList = new ArrayList<>();
            holder.horizontalAdapter = new HorizontalAdapter(getContext(), holder.productList);
            horizontalListView.setAdapter(holder.horizontalAdapter);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        VerticalListItemWrapper item = getItem(position);
        if (item != null) {

            holder.productList.clear();
            holder.productList.addAll(Arrays.asList(item.getProducts()));
            holder.horizontalAdapter.notifyDataSetChanged();
        }

        return convertView;
    }

    private class ViewHolder {
        public HorizontalAdapter horizontalAdapter;
        public ArrayList<Product> productList;
    }
}
