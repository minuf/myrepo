package com.minuf.minuf.socialnetworksample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minuf.minuf.socialnetworksample.R;
import com.minuf.minuf.socialnetworksample.ui.adapters.MainList_Posts_Adapter;
import com.minuf.minuf.socialnetworksample.ui.anim_deco.DividerItemDecoration;

/**
 * Created by jorge on 11/09/15.
 */
public class Frag_list_posts extends Fragment {


    //empty constructor
    public Frag_list_posts() {
    }

    //newinstance() method for create the fragments
    public static Frag_list_posts newInstance(){
        //call constructor for create
        Frag_list_posts frag = new Frag_list_posts();
        //create bundle and set args to fragment for rescue later

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // get the recyclerview from her layout
        final RecyclerView list = (RecyclerView)inflater.inflate(R.layout.f_recyclerview, container, false);


        //CREATE ADAPTER, SETS ONCLICK AND SETS TO LIST
        MainList_Posts_Adapter adapter = new MainList_Posts_Adapter();

        list.swapAdapter(adapter, true);
        list.setHasFixedSize(true);

        //list.getVerticalScrollbarPosition();

        //sets the layout manager, decoration and animation for correcty implementation of recyclerview ( recycler require that)
        list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        list.setItemAnimator(new DefaultItemAnimator());
        list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        final LinearLayoutManager llm = (LinearLayoutManager)list.getLayoutManager();


        list.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
               // Log.e("STATE", "" + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
               // Log.e("ITEM", ""+llm.findFirstCompletelyVisibleItemPosition());
            }
        });



        return list;
    }

}
