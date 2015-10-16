package com.minuf.mynodeproyectclient.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.minuf.mynodeproyectclient.R;
import com.minuf.mynodeproyectclient.ui.fragments.Fragment_Create;
import com.minuf.mynodeproyectclient.ui.fragments.Fragment_Delete;
import com.minuf.mynodeproyectclient.ui.fragments.Fragment_Read;
import com.minuf.mynodeproyectclient.ui.fragments.Fragment_Update;

public class CrudActivity extends AppCompatActivity {

    String extra = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crud);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        extra = getIntent().getExtras().getString("ACTION");
    }

    @Override
    protected void onResume() {
        super.onResume();

        switch (extra) {
            case "C": replaceFragment(new Fragment_Create(), "FragmentCreate");
                break;
            case "R": replaceFragment(new Fragment_Read(), "FragmentRead");
                break;
            case "U": replaceFragment(new Fragment_Update(), "FragmentUpdate");
                break;
            case "D": replaceFragment(new Fragment_Delete(), "FragmentDelete");
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id){
            case R.id.action_create: extra = "C";
                break;
            case R.id.action_read: extra = "R";
                break;
            case R.id.action_update: extra = "U";
                break;
            case R.id.action_delete: extra = "D";
                break;
        }

        onResume();

        return super.onOptionsItemSelected(item);
    }



    //custom methods

    public void addFragment(Fragment fragment, String tag){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.lay_container, fragment,tag)
                .commit();
    }

    public void replaceFragment(Fragment fragment, String tag){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.lay_container, fragment, tag)
                .commit();
    }

}
