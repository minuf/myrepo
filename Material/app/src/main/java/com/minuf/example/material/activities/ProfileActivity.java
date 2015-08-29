package com.minuf.example.material.activities;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.minuf.example.material.R;
import com.minuf.example.material.adapters.List2_Profile_Adapter;
import com.minuf.example.material.adapters.MainListAdapter;
import com.minuf.example.material.anim_deco.DividerItemDecoration;
import com.minuf.example.material.items_struc.ItemList1_Structure;
import com.minuf.example.material.items_struc.ItemList2_Structure;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jorge on 26/08/15.
 */
public class ProfileActivity extends AppCompatActivity {

    private FloatingActionButton btnFab;

    private final int DEVICE_SDK = Build.VERSION.SDK_INT;
    ImageView iv_profile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        if (DEVICE_SDK >= Build.VERSION_CODES.LOLLIPOP) setStatusBarColorLOLLIPOP();

        /** LOAD IMAGE FROM URL WITH PICASSO LIBRARY  **/
        iv_profile = (ImageView)findViewById(R.id.iv_profileToolbar);

        Picasso.with(this)
                .load("http://whosbehindmask.weebly.com/uploads/2/8/3/6/28365549/5831103_orig.jpg")    //http://viralandscdn.net/posts/13668/image-sg3SqUON.jpg
                .into(iv_profile);
        /*************      ***********/

        /******  Floating Action Button      **************/
        btnFab = (FloatingActionButton)findViewById(R.id.btnFab);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Esto es una prueba", Snackbar.LENGTH_LONG).show();
            }
        });

        /**  App bar  **/
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar2);
        setSupportActionBar(toolbar);
        //TO SET TITLE TO ACTIONBAR, REFERENCE TO COLLAPSINGTOOLBARLAYOUT (TOOLBAR PARENT) AND SETS TITLE
        CollapsingToolbarLayout ctlLayout
                = (CollapsingToolbarLayout)findViewById(R.id.ctlLayout);
        ctlLayout.setTitle("Name LastName");

        /**     LIST       **/

        // get the recyclerview from her layout
        RecyclerView list = (RecyclerView)findViewById(R.id.list_profile);

        // get the items count from bundle
        //int itemsCount = getArguments().getInt(ITEMS_COUNT_KEY);
        //create arraylist with all data objects and set to adapter to list for show it later in views
        ArrayList<ItemList2_Structure> arrayData = new ArrayList<>();
        for (int i=0; i<90; i++){
            arrayData.add(new ItemList2_Structure("Titulo", "Subtitulo"));
        }
        List2_Profile_Adapter adapter = new List2_Profile_Adapter(arrayData);
        list.setAdapter(adapter);

        //sets the layout manager, decoration and animation for correcty implementation of recyclerview ( recycler require that)
        list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        list.setItemAnimator(new DefaultItemAnimator());
        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColorLOLLIPOP() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.RED);
    }
    public void getProminentColorFromImage(){

       /** Bitmap bitmap = ((BitmapDrawable)iv_profile.getDrawable()).getBitmap();

        // Synchronous
        Palette p = Palette.from(bitmap).generate();

        int color = p.getVibrantColor(Color.BLUE); */

        /*// Asynchronous
        Palette.from(bitmap).generate(new PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                // Use generated instance
            }
        });*/
    }
}
