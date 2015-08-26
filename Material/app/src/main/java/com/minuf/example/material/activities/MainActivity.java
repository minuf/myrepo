package com.minuf.example.material.activities;

import android.content.res.Configuration;
import android.graphics.Color;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.minuf.example.material.R;
import com.minuf.example.material.adapters.FragmentsAdapter;
import com.minuf.example.material.fragments.Frag_list_main;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ScrimInsetsFrameLayout sifl;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView ndList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main_activity);

        showAppBarAndNavigationDrawer();
        showViewPagerAndTabLayout();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    /************************************
     *                  CUSTOM METHODS FOR CREATE UI STRUCTURE
     *                              *********************************************/

    public void showAppBarAndNavigationDrawer() {
        /**  App bar  **/
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Other title");

        /********   Navigation Drawer  ********/

        /**Referencia al ScrimInsetsFrameLayout**/
        sifl = (ScrimInsetsFrameLayout)findViewById(R.id.scrimInsetsFrameLayout);

        /**Menu del Navigation Drawer (ListView)**/
        ndList = (ListView)findViewById(R.id.navdrawerlist);

        final String[] opciones = new String[]{"Option 1", "Option 2", "Option 3"};

        ArrayAdapter<String> ndMenuAdapter =
                new ArrayAdapter<>(this,android.R.layout.simple_list_item_activated_1, opciones);
        /**ArrayAdapter<String> ndMenuAdapter =
         new ArrayAdapter<>(this,android.R.layout.simple_list_item1, opciones);**/
        ndList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Snackbar.make(view, "Option 1", Snackbar.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Snackbar.make(view, "Option 2", Snackbar.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Snackbar.make(view, "Option 3", Snackbar.LENGTH_SHORT).show();
                        break;
                    default:
                }
                ndList.setItemChecked(position, true);

                getSupportActionBar().setTitle(opciones[position]);

                drawerLayout.closeDrawer(sifl);
            }
        });
        ndList.setAdapter(ndMenuAdapter);

        /** LOAD IMAGE FROM URL WITH PICASSO LIBRARY  **/
        ImageView iv_drawer = (ImageView)findViewById(R.id.iv_drawer);

        Picasso.with(this)
                .load("http://whosbehindmask.weebly.com/uploads/2/8/3/6/28365549/5831103_orig.jpg")    //http://viralandscdn.net/posts/13668/image-sg3SqUON.jpg
                .into(iv_drawer);

        /**********         **************/
        Picasso.with(this).setIndicatorsEnabled(true);
        //Drawer Layout
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        drawerLayout.setStatusBarBackgroundColor(
                getResources().getColor(R.color.color_primary_dark));

        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);
        /** To show navdrawer icon on appbar and open drawer with this button **/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void showViewPagerAndTabLayout() {
        /**  ViewPager  get viewpager from id and set adapter**/
        ViewPager viewPager = (ViewPager)findViewById(R.id.content_viewPager);
        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager());
        adapter.addFragment(Frag_list_main.newInstance(50));
        adapter.addFragment(Frag_list_main.newInstance(80));
        adapter.addFragment(Frag_list_main.newInstance(20));
        viewPager.setAdapter(adapter);

        /**  TabLayout  get the tablayout for setup with viewPager, add icons to tabs and set mode FIXED or SCROLLABLE(this is very important for correcly performance)**/
        TabLayout tabs = (TabLayout)findViewById(R.id.tabs_bar);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setupWithViewPager(viewPager);
        //set the icons or text
        tabs.getTabAt(0).setText("TAB 1");
        tabs.getTabAt(1).setText("TAB 2");
        tabs.getTabAt(2).setText("TAB 3");
        //change color and height to selected tab indicator
        tabs.setSelectedTabIndicatorColor(Color.WHITE);
        tabs.setSelectedTabIndicatorHeight(15);
    }

    /******************     END METHODS     ****************/
}