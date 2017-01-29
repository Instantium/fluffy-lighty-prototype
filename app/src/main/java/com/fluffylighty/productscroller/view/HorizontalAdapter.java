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
import com.fluffylighty.productscroller.Utilities.Utilities;
import com.fluffylighty.productscroller.model.ImageInfo;
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

            String currencySymbol = currentItem.getCurrency().getSymbol();
            String formattedProductPrice = Utilities.formatPrice(currentItem.getPrice());

            String priceTag = getContext().getString(R.string.product_price_with_currency_symbol, formattedProductPrice, currencySymbol);

            holder.productPricingTextView.setText(priceTag);

            holder.productImageView.setImageResource(R.drawable.placeholder_drawable);
            String imageUrl = getImageUrl(currentItem.getImages());

            if (imageUrl != null) {
                ImageLoader.getInstance().displayImage(imageUrl, holder.productImageView);
            }
        }

        return convertView;
    }

    private String getImageUrl(ImageInfo[] imageInfos) {

        if (imageInfos == null) {
            return null;
        }

        String finalUrl = null;

        for (ImageInfo imageInfo : imageInfos) {

            String url = imageInfo.getUrl();
            if (url != null && !url.isEmpty()) {

                if (imageInfo.isPrimary()) {

                    //If there is a Image marked as primary, this will be used instead of any other
                    return url;
                } else {

                    finalUrl = url;
                }
            }
        }

        return finalUrl;
    }

    private class ViewHolder {
        public TextView productPricingTextView;
        public TextView productNameTextView;
        public ImageView productImageView;
    }
}
