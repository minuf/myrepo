package com.minuf.mysocialnetwork.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minuf.mysocialnetwork.R;

/**
 * Created by jorge on 26/10/15.
 */
public class Fragment_SignUpForm extends Fragment {

    //empty constructor
    public Fragment_SignUpForm(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_singup_form, container, false);
        return v;
    }
}
