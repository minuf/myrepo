package com.minuf.mysocialnetwork.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.minuf.mysocialnetwork.R;
import com.minuf.mysocialnetwork.ui.adapters.FragmentsAdapter;
import com.minuf.mysocialnetwork.ui.fragments.Fragment_Content_Contacts;
import com.minuf.mysocialnetwork.ui.fragments.Fragment_Content_People;
import com.minuf.mysocialnetwork.ui.fragments.Fragment_Content_Timeline;

public class ContentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /**  ViewPager  get viewpager from id and set adapter**/
        ViewPager viewPager = (ViewPager)findViewById(R.id.content_viewPager);
        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment_Content_People());
        adapter.addFragment(new Fragment_Content_Timeline());
        adapter.addFragment(new Fragment_Content_Contacts());
        viewPager.setAdapter(adapter);

        /**  TabLayout  get the tablayout for setup with viewPager, add icons to tabs and set mode FIXED or SCROLLABLE(this is very important for correcly performance)**/
        TabLayout tabs = (TabLayout)findViewById(R.id.tabs_bar);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setupWithViewPager(viewPager);
        //set the icons or text
        tabs.getTabAt(0).setText("PEOPLE");
        tabs.getTabAt(1).setText("TIMELINE").select();
        tabs.getTabAt(2).setText("CONTACTS");
        //change color and height to selected tab indicator
        tabs.setSelectedTabIndicatorColor(Color.WHITE);
        tabs.setSelectedTabIndicatorHeight(12);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            SharedPreferences.Editor editor = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE).edit();
            editor.remove("EMAIL");
            editor.remove("TOKEN");
            editor.remove("DEVICE_ID");
            editor.commit();

            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
