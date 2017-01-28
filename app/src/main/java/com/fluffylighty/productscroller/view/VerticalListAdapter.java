package com.fluffylighty.productscroller.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fluffylighty.productscroller.R;
import com.fluffylighty.productscroller.model.Post;
import com.fluffylighty.productscroller.model.Product;
import com.fluffylighty.productscroller.model.VerticalListItemWrapper;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nico Adler on 28.01.17.
 */
public class VerticalListAdapter extends BaseAdapter {

    private static final int PRODUCT_VIEW_TYPE = 1;
    private static final int POST_VIEW_TYPE = 0;
    private final Context context;
    private final List<VerticalListItemWrapper> verticalListItemWrappers;

    public VerticalListAdapter(Context context, List<VerticalListItemWrapper> verticalListItemWrappers) {
        super();

        this.context = context;
        this.verticalListItemWrappers = verticalListItemWrappers;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        VerticalListItemWrapper item = getItem(position);

        if (item.isPost()) {

            return POST_VIEW_TYPE;
        } else {
            return PRODUCT_VIEW_TYPE;
        }
    }

    @Override
    public int getCount() {
        return verticalListItemWrappers.size();
    }

    @Override
    public VerticalListItemWrapper getItem(int position) {
        return verticalListItemWrappers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        VerticalListItemWrapper currentItem = getItem(position);

        if (currentItem.isPost()) {

            convertView = getPostView(currentItem, convertView);
        } else {
            convertView = getProductView(currentItem, convertView);
        }

        return convertView;
    }

    @NonNull
    private View getPostView(VerticalListItemWrapper currentItem, View convertView) {

        PostViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.vertical_listview_post_list_item, null);

            holder = new PostViewHolder();

            holder.categoryNameTextView = (TextView) convertView.findViewById(R.id.post_list_item_category_name_text_view);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.post_list_item_title_text_view);
            holder.imageView = (ImageView) convertView.findViewById(R.id.post_list_item_image_view);

            convertView.setTag(holder);
        } else {
            holder = (PostViewHolder) convertView.getTag();
        }

        Post post = currentItem.getPost();

        //TODO display categoryName
        holder.categoryNameTextView.setText(post.getTitle());
        holder.titleTextView.setText(post.getTitle());


        //TODO show placeholder image
        ImageLoader.getInstance().displayImage(post.getTeaserImage(), holder.imageView);

        return convertView;
    }

    @NonNull
    private View getProductView(VerticalListItemWrapper currentItem, View convertView) {

        ProductViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.vertical_listview_horizontal_listview_item, null);

            holder = new ProductViewHolder();
            TwoWayView horizontalListView = (TwoWayView) convertView.findViewById(R.id.horizontal_listview);
            holder.productList = new ArrayList<>();
            holder.horizontalAdapter = new HorizontalAdapter(context, holder.productList);
            horizontalListView.setAdapter(holder.horizontalAdapter);

            convertView.setTag(holder);
        } else {
            holder = (ProductViewHolder) convertView.getTag();
        }

        holder.productList.clear();
        holder.productList.addAll(currentItem.getProducts());
        holder.horizontalAdapter.notifyDataSetChanged();

        return convertView;
    }

    private class ProductViewHolder {
        HorizontalAdapter horizontalAdapter;
        ArrayList<Product> productList;
    }

    private class PostViewHolder {
        TextView categoryNameTextView;
        ImageView imageView;
        TextView titleTextView;
    }
}
