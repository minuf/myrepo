package com.minuf.example.material.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minuf.example.material.R;
import com.minuf.example.material.adapters.MainListAdapter;
import com.minuf.example.material.anim_deco.DividerItemDecoration;
import com.minuf.example.material.items_struc.ItemList1_Structure;

import java.util.ArrayList;

/**
 * Created by jorge on 14/08/15.
 */
public class Frag_list_main extends Fragment {

    public final static String ITEMS_COUNT_KEY = "ItemsCount";

    //empty constructor
    public Frag_list_main() {
    }

    //newinstance() method for create the fragments
    public static Frag_list_main newInstance(int itemsCount){
        //call constructor for create
        Frag_list_main frag = new Frag_list_main();
        //create bundle and set args to fragment for rescue later
        Bundle bundle = new Bundle();
        bundle.putInt(ITEMS_COUNT_KEY, itemsCount);
        frag.setArguments(bundle);

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // get the recyclerview from her layout
        RecyclerView list = (RecyclerView)inflater.inflate(R.layout.frag_list, container, false);

        // get the items count from bundle
        int itemsCount = getArguments().getInt(ITEMS_COUNT_KEY);
        //create arraylist with all data objects and set to adapter to list for show it later in views
        ArrayList<ItemList1_Structure> arrayData = new ArrayList<>();
        for (int i=0; i<itemsCount; i++){
            arrayData.add(new ItemList1_Structure(R.drawable.selfie2, "Title "+(i+1), "Subtitle "+(i+1), i+1));
        }
        MainListAdapter adapter = new MainListAdapter(arrayData);
        list.setAdapter(adapter);

        //sets the layout manager, decoration and animation for correcty implementation of recyclerview ( recycler require that)
        list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        list.setItemAnimator(new DefaultItemAnimator());
        list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        return list;
    }

}
