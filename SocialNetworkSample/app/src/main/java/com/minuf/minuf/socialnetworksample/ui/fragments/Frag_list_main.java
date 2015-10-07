package com.minuf.minuf.socialnetworksample.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minuf.minuf.socialnetworksample.R;
import com.minuf.minuf.socialnetworksample.ui.adapters.MainListAdapter;
import com.minuf.minuf.socialnetworksample.ui.anim_deco.DividerItemDecoration;

/**
 * Created by jorge on 9/09/15.
 */
public class Frag_list_main extends Fragment {


    //empty constructor
    public Frag_list_main() {
    }

    //newinstance() method for create the fragments
    public static Frag_list_main newInstance(){
        //call constructor for create
        Frag_list_main frag = new Frag_list_main();
        //create bundle and set args to fragment for rescue later

        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // get the recyclerview from her layout
        final RecyclerView list = (RecyclerView)inflater.inflate(R.layout.f_recyclerview, container, false);


        //CREATE ADAPTER, SETS ONCLICK AND SETS TO LIST
        MainListAdapter adapter = new MainListAdapter();
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Pulsado el item: " + (1 + list.getChildAdapterPosition(v)), Snackbar.LENGTH_SHORT).show();
            }
        });
        list.setAdapter(adapter);

        //sets the layout manager, decoration and animation for correcty implementation of recyclerview ( recycler require that)
        list.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        list.setItemAnimator(new DefaultItemAnimator());
        list.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        return list;
    }

}
