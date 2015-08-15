package com.selfiedrop.selfiedrop.selfiedrop.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.selfiedrop.selfiedrop.selfiedrop.R;
import com.selfiedrop.selfiedrop.selfiedrop.adapters.MainListAdapter;
import com.selfiedrop.selfiedrop.selfiedrop.anim_deco.DividerItemDecoration;
import com.selfiedrop.selfiedrop.selfiedrop.items_struc.ItemListMainStruc;

import java.util.ArrayList;

/**
 * Created by jorge on 14/08/15.
 */
public class FragHome extends Fragment {

    public final static String ITEMS_COUNT_KEY = "ItemsCount";

    //empty constructor
    public FragHome() {
    }

    //newinstance() method for create the fragments
    public static FragHome newInstance(int itemsCount){
        //call constructor for create
        FragHome frag = new FragHome();
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
        RecyclerView list = (RecyclerView)inflater.inflate(R.layout.frag_home, container, false);

        // get the items count from bundle
        int itemsCount = getArguments().getInt(ITEMS_COUNT_KEY);
        //create arraylist with all data objects and set to adapter to list for show it later in views
        ArrayList <ItemListMainStruc> arrayData = new ArrayList<>();
        for (int i=0; i<itemsCount; i++){
            arrayData.add(new ItemListMainStruc("Title "+(i+1), "Subtitle "+(i+1), i+1));
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
