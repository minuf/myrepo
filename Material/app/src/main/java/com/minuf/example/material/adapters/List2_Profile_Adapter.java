package com.minuf.example.material.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minuf.example.material.R;
import com.minuf.example.material.items_struc.ItemList1_Structure;
import com.minuf.example.material.items_struc.ItemList2_Structure;
import com.minuf.example.material.views.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jorge on 26/08/15.
 */
public class List2_Profile_Adapter extends RecyclerView.Adapter<List2_Profile_Adapter.ItemViewHolder> {

    private ArrayList<ItemList2_Structure> arrayData;
    //private static Context context;

    //constructor
    public List2_Profile_Adapter(ArrayList<ItemList2_Structure> arrayData) {
        //initialize array
        this.arrayData = arrayData;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflate the view and set to holder
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item2_list, viewGroup, false);

        ItemViewHolder holder = new ItemViewHolder(itemView);

        //context = viewGroup.getContext();

        return holder;

    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        //rescue item from her ArrayList position and bind calling custom method bindItem(item);
        ItemList2_Structure item = arrayData.get(i);

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

        //Holder constructor, initialize views
        public ItemViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView)itemView.findViewById(R.id.tv_list2);
            tv_subtitle = (TextView)itemView.findViewById(R.id.tv2_list2);
        }
        //custom method for bind view with text content, that can be writted on override method 'onBindViewHolder()', but it only call this method for cleaner code
        public void bindItem(ItemList2_Structure item) {
            tv_title.setText(item.getTitle());
            tv_subtitle.setText(item.getSubtitle());
        }
    }
}