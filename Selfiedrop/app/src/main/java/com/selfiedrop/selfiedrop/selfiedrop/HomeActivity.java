package com.selfiedrop.selfiedrop.selfiedrop;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.selfiedrop.selfiedrop.selfiedrop.adapters.FragmentsAdapter;
import com.selfiedrop.selfiedrop.selfiedrop.fragments.FragHome;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /**  App bar  **/
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Other title");

        /**  ViewPager  get viewpager from id and set adapter**/
        ViewPager viewPager = (ViewPager)findViewById(R.id.content_viewPager);
        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager());
        adapter.addFragment(FragHome.newInstance(20));
        adapter.addFragment(FragHome.newInstance(20));
        adapter.addFragment(FragHome.newInstance(20));
        viewPager.setAdapter(adapter);

        /**  TabLayout  get the tablayout for setup with viewPager, add icons to tabs and set mode FIXED or SCROLLABLE(this is very important for correcly performance)**/
        TabLayout tabs = (TabLayout)findViewById(R.id.tabs_bar);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setupWithViewPager(viewPager);
        //set the icons
        tabs.getTabAt(0).setIcon(R.mipmap.ic_tabs_people);
        tabs.getTabAt(1).setIcon(R.mipmap.ic_tabs_wall);
        tabs.getTabAt(2).setIcon(R.mipmap.ic_tabs_act);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
