package com.minuf.minuf.socialnetworksample.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jorge on 14/08/15.
 */
public class FragmentsAdapter extends FragmentPagerAdapter {
    //create and initialize fragments arraylist
    private final List<Fragment> fragmentList = new ArrayList<>();

    //default constructor
    public FragmentsAdapter(FragmentManager fm) {
        super(fm);
    }

    //custom method for add fragments to list
    public void addFragment(Fragment fragment){
        fragmentList.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
