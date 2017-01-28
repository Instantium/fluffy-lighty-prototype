package com.fluffylighty.productscroller.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fluffylighty.productscroller.R;
import com.fluffylighty.productscroller.model.Product;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class HorizontalAdapter extends ArrayAdapter<Product> {

    public HorizontalAdapter(Context context, ArrayList<Product> products) {
        super(context, -1, products);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.horizontal_listview_item, null);

            holder = new ViewHolder();
            holder.productNameTextView = (TextView) convertView.findViewById(R.id.horizontal_listview_list_item_name_text_view);
            holder.productPricingTextView = (TextView) convertView.findViewById(R.id.horizontal_listview_list_item_pricing_text_view);
            holder.productImageView = (ImageView) convertView.findViewById(R.id.horizontal_listview_list_item_image_view);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Product currentItem = getItem(position);

        if (currentItem != null) {
            holder.productNameTextView.setText(currentItem.getName());
            holder.productPricingTextView.setText(currentItem.getPrice() + "");

            String imageUrl = currentItem.getImages()[0].getUrl();

            ImageLoader.getInstance().displayImage(imageUrl, holder.productImageView);
        }

        return convertView;
    }

    private class ViewHolder {
        public TextView productPricingTextView;
        public TextView productNameTextView;
        public ImageView productImageView;
    }
}
