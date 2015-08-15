package com.selfiedrop.selfiedrop.selfiedrop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.selfiedrop.selfiedrop.selfiedrop.R;
import com.selfiedrop.selfiedrop.selfiedrop.items_struc.ItemListMainStruc;

import java.util.ArrayList;

/**
 * Created by jorge on 14/08/15.
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ItemViewHolder> {

    private ArrayList<ItemListMainStruc> arrayData;

    //constructor
    public MainListAdapter(ArrayList<ItemListMainStruc> arrayData) {
        //initialize array
        this.arrayData = arrayData;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflate the view and set to holder
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_wall_list, viewGroup, false);

        ItemViewHolder holder = new ItemViewHolder(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        //rescue item from her ArrayList position and bind calling custom method bindItem(item);
        ItemListMainStruc item = arrayData.get(i);

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
            tv_title = (TextView)itemView.findViewById(R.id.item_title);
            tv_subtitle = (TextView)itemView.findViewById(R.id.item_subtitle);
        }
        //custom method for bind view with text content, that can be writted on override method 'onBindViewHolder()', but it only call this method for cleaner code
        public void bindItem(ItemListMainStruc item) {
            tv_title.setText(item.getTitle());
            tv_subtitle.setText(item.getSubtitle());
        }
    }
}
