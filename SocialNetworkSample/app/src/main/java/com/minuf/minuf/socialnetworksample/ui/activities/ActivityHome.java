package com.minuf.minuf.socialnetworksample.ui.activities;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.minuf.minuf.socialnetworksample.R;
import com.minuf.minuf.socialnetworksample.ui.adapters.FragmentsAdapter;
import com.minuf.minuf.socialnetworksample.ui.fragments.Frag_list_main;
import com.minuf.minuf.socialnetworksample.ui.fragments.Frag_list_posts;

public class ActivityHome extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //setupWindowAnimations();  //enter and exit activity transitions

        showViewPagerAndTabLayout();
        showAppBarAndNavigationDrawer();
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

        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_settings:
                return true;
            //...
        }
        return super.onOptionsItemSelected(item);
    }

    /************************************
     * CUSTOM METHODS FOR CREATE UI STRUCTURE
     *********************************************/

    public void showAppBarAndNavigationDrawer() {
        /**  App bar  **/
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Other title");

        /********   Navigation Drawer  ********/
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.navview);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {


                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                Snackbar.make(navView, "Pulsada seccion 1", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.menu_seccion_2:
                                Snackbar.make(navView, "Pulsada seccion 2", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.menu_seccion_3:
                                Snackbar.make(navView, "Pulsada seccion 3", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.menu_opcion_1:
                                Log.i("NavigationView", "Pulsada opción 1");
                                break;
                            case R.id.menu_opcion_2:
                                Log.i("NavigationView", "Pulsada opción 2");
                                break;
                        }
                        menuItem.setChecked(true);
                        getSupportActionBar().setTitle(menuItem.getTitle());


                        drawerLayout.closeDrawers();

                        return true;
                    }
                });

    }

    public void showViewPagerAndTabLayout() {
        /**  ViewPager  get viewpager from id and set adapter**/
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_content_viewPager);
        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager());
        adapter.addFragment(Frag_list_posts.newInstance());
        adapter.addFragment(Frag_list_main.newInstance());
        adapter.addFragment(Frag_list_main.newInstance());
        viewPager.setAdapter(adapter);

        /**  TabLayout  get the tablayout for setup with viewPager, add icons to tabs and set mode FIXED or SCROLLABLE(this is very important for correcly performance)**/
        TabLayout tabs = (TabLayout) findViewById(R.id.main_tabs_bar);
        //tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setupWithViewPager(viewPager);
        //set the icons or text
        tabs.getTabAt(0).setText("TAB 1");
        tabs.getTabAt(1).setText("TAB 2");
        tabs.getTabAt(2).setText("TAB 3");
        //change color and height to selected tab indicator
        tabs.setSelectedTabIndicatorColor(Color.WHITE);
        tabs.setSelectedTabIndicatorHeight(12);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        Log.e("ANIM", "animadoooo");
        Explode explode = new Explode();
        explode.setDuration(2000);
        getWindow().setExitTransition(explode);
        getWindow().setEnterTransition(explode);
    }

}
