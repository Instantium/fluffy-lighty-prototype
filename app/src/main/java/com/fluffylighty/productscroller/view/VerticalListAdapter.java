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
import com.fluffylighty.productscroller.Utilities.Utilities;
import com.fluffylighty.productscroller.model.Post;
import com.fluffylighty.productscroller.model.PostCategory;
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

    private static final int SECTION_HEADER_VIEW_TYPE = 2;
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

        //There are the same amount of ViewTypes as there are WrapperTypes
        return VerticalListItemWrapper.WrapperType.values().length;
    }

    @Override
    public int getItemViewType(int position) {

        VerticalListItemWrapper item = getItem(position);

        //Since there are as many ViewTypes as Enum-constants the ordinal is enough to identify the ViewType.
        return item.getWrapperType().ordinal();
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

        switch (currentItem.getWrapperType()) {
            case POST:
                convertView = getPostView(currentItem, convertView);
                break;
            case PRODUCT_LIST:
                convertView = getProductView(currentItem, convertView);
                break;
            default:
            case SECTION_HEADER:
                convertView = getSectionHeaderView(currentItem, convertView);
                break;
        }

        return convertView;
    }

    @NonNull
    private View getSectionHeaderView(VerticalListItemWrapper currentItem, View convertView) {

        SectionHeaderViewHolder holder;
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.vertical_listview_section_header_list_item, null);

            holder = new SectionHeaderViewHolder();

            holder.titleTextView = (TextView) convertView.findViewById(R.id.section_header_title_text_view);
            holder.subtitleTextView = (TextView) convertView.findViewById(R.id.section_header_subtitle_text_view);

            convertView.setTag(holder);
        } else {
            holder = (SectionHeaderViewHolder) convertView.getTag();
        }

        holder.titleTextView.setText(currentItem.getSectionHeaderTitleResId());
        holder.subtitleTextView.setText(currentItem.getSectionHeaderSubtitleResId());

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

        PostCategory category = post.getCategory();

        holder.categoryNameTextView.setText(category.getName());
        String colorString = category.getColor();
        holder.categoryNameTextView.setTextColor(Utilities.parseColorString(colorString));

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

    private class SectionHeaderViewHolder {
        TextView titleTextView;
        TextView subtitleTextView;
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
