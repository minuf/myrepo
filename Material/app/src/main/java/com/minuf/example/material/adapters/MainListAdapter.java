package com.minuf.example.material.adapters;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minuf.example.material.R;
import com.minuf.example.material.items_struc.ItemList1_Structure;
import com.minuf.example.material.views.RoundedImageView;

import java.util.ArrayList;

/**
 * Created by jorge on 14/08/15.
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ItemViewHolder> {

    private ArrayList<ItemList1_Structure> arrayData;

    //constructor
    public MainListAdapter(ArrayList<ItemList1_Structure> arrayData) {
        //initialize array
        this.arrayData = arrayData;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflate the view and set to holder
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item1_list, viewGroup, false);

        ItemViewHolder holder = new ItemViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        //rescue item from her ArrayList position and bind calling custom method bindItem(item);
        ItemList1_Structure item = arrayData.get(i);

        itemViewHolder.bindItem(item);
    }

    @Override
    public int getItemCount() {
        //get the total of items count
        return arrayData.size();
    }

    // subclass for Holder the views (required on ReciclerView)
    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        //create views
        private TextView tv_title, tv_subtitle;
        private RoundedImageView iv_image;

        //Holder constructor, initialize views
        public ItemViewHolder(View itemView) {
            super(itemView);
            iv_image = (RoundedImageView)itemView.findViewById(R.id.item_photo);
            tv_title = (TextView)itemView.findViewById(R.id.item_title);
            tv_subtitle = (TextView)itemView.findViewById(R.id.item_subtitle);
        }
        //custom method for bind view with text content, that can be writted on override method 'onBindViewHolder()', but it only call this method for cleaner code
        public void bindItem(ItemList1_Structure item) {

            /*Bitmap icon = BitmapFactory.decodeResource(Resources.getSystem(), item.getImage());

            iv_image.setImageBitmap(icon);*/

            iv_image.setImageResource(item.getImage());
            tv_title.setText(item.getTitle());
            tv_subtitle.setText(item.getSubtitle());
        }
    }
}
