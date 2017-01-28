package com.fluffylighty.productscroller.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.fluffylighty.productscroller.R;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class HorizontalAdapter extends ArrayAdapter<String> {

    public HorizontalAdapter(Context context, String[] strings) {
        super(context, -1, strings);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.horizontal_listview_item, null);

            holder = new ViewHolder();

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        return convertView;
    }

    private class ViewHolder {
    }
}
