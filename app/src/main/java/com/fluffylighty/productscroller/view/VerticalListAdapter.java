package com.fluffylighty.productscroller.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.fluffylighty.productscroller.R;

import org.lucasr.twowayview.TwoWayView;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class VerticalListAdapter extends ArrayAdapter<String> {

    private final String[] strings;

    public VerticalListAdapter(Context context, String[] strings) {
        super(context, -1, strings);
        this.strings = strings;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vertical_listview_horizontal_listview_item, null);

            holder = new ViewHolder();
            TwoWayView horizontalListView = (TwoWayView) convertView.findViewById(R.id.horizontal_listview);
            ArrayAdapter<String> horizontalAdapter = new ArrayAdapter<String>(getContext(), R.layout.horizontal_listview_item, strings);
            horizontalListView.setAdapter(horizontalAdapter);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    private class ViewHolder {
    }
}
