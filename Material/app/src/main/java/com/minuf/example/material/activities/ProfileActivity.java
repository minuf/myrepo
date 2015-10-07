package com.minuf.example.material.activities;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.minuf.example.material.R;
import com.minuf.example.material.adapters.List2_Profile_Adapter;
import com.minuf.example.material.anim_deco.DividerItemDecoration;
import com.minuf.example.material.classes.GlobalSingleton;
import com.minuf.example.material.classes.MyApplication;
import com.minuf.example.material.items_struc.ItemList2_Structure;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

/**
 * Created by jorge on 26/08/15.
 */
public class ProfileActivity extends AppCompatActivity {

    private FloatingActionButton btnFab;

    private final int DEVICE_SDK = Build.VERSION.SDK_INT;
    ImageView iv_profile;
    RecyclerView list;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        //SETSTATUSBARCOLOR CUSTOM METHOD
        if (DEVICE_SDK >= Build.VERSION_CODES.LOLLIPOP) setStatusBarColorLOLLIPOP();

        /** LOAD IMAGE FROM URL WITH PICASSO LIBRARY  **/
        iv_profile = (ImageView)findViewById(R.id.iv_profileToolbar);

        GlobalSingleton.getInstance().loadImageFromPicasso(this, iv_profile);
       /** Picasso.with(this)
                .load("http://whosbehindmask.weebly.com/uploads/2/8/3/6/28365549/5831103_orig.jpg")    //http://viralandscdn.net/posts/13668/image-sg3SqUON.jpg
                .into(iv_profile);*/
        //LISTENER
        final Intent intent = new Intent(ProfileActivity.this, Activity_FullScreenPhoto.class);
        if (DEVICE_SDK >= Build.VERSION_CODES.LOLLIPOP) {
            iv_profile.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(ProfileActivity.this, v, v.getTransitionName());

                    startActivity(intent, options.toBundle());
                }
            });
        } else {
            iv_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
        }
        /*************      ***********/



        /**  App bar  **/
        Toolbar toolbar = (Toolbar)findViewById(R.id.app_bar2);
        setSupportActionBar(toolbar);
        //TO SET TITLE TO ACTIONBAR, REFERENCE TO COLLAPSINGTOOLBARLAYOUT (TOOLBAR PARENT) AND SETS TITLE
        CollapsingToolbarLayout ctlLayout
                = (CollapsingToolbarLayout)findViewById(R.id.ctlLayout);
        ctlLayout.setTitle("Name LastName");

        /**     LIST       **/

        // get the recyclerview from her layout
        list = (RecyclerView)findViewById(R.id.list_profile);

        // get the items count from bundle
        //int itemsCount = getArguments().getInt(ITEMS_COUNT_KEY);
        //create arraylist with all data objects and set to adapter to list for show it later in views
        ArrayList<ItemList2_Structure> arrayData = new ArrayList<>();
        for (int i=0; i<90; i++){
            arrayData.add(new ItemList2_Structure("Titulo "+(i+1), "Subtitulo "+(i+1)));
        }
        List2_Profile_Adapter adapter = new List2_Profile_Adapter(arrayData);
        list.setAdapter(adapter);

        //sets the layout manager, decoration and animation for correcty implementation of recyclerview ( recycler require that)
        //list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //list.setLayoutManager(new GridLayoutManager(this, 3));
        list.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        list.setItemAnimator(new DefaultItemAnimator()); //important, when add or remove item from list, it animates
        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST)); //optional
        list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST)); //optional
        //list.setHasFixedSize(true); //with this parameter, list performance are more quick (recomended)

        /******  Floating Action Button      **************/
        btnFab = (FloatingActionButton)findViewById(R.id.btnFab);
        btnFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Esto es una prueba", Snackbar.LENGTH_LONG).show();
                Intent i = new Intent(ProfileActivity.this, Activity_NestedScroll_Sample.class);
                startActivity(i);
            }
        });
        btnFab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Snackbar.make(v, "TEST", Snackbar.LENGTH_SHORT).show();
                list.setLayoutManager(new LinearLayoutManager(ProfileActivity.this, LinearLayoutManager.VERTICAL, false));
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //METHOD FOR SET STATUS BAR COLOR (ONLY LOLLIPOP). Inside, load color from image (VibrantColor, MutedColor.....)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatusBarColorLOLLIPOP() {
        final Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //window.setStatusBarColor(Color.RED);

        /* GETTIN COLOR FROM IMAGE
        *FOR LOAD IMAGE FROM PICASSO AND GET BITMAP BEFORE SET IMAGE ON VIEW, LOAD IMAGE INTO NEW TARGET AND IN ONBITMAPLOADED METHOD CAN GET BITMAP FROM FIRST ENTER PARAMETER
        *Para leer una imagen de Picasso y almacenarla en un bitmap antes de que la introduzca en la view, se lee la imagen dentro de un objeto Target y en el metodo onBitmapLoaded recuperamos el bitmap desde el primer parametro de entrada.
        */
        Picasso.with(this).load("http://whosbehindmask.weebly.com/uploads/2/8/3/6/28365549/5831103_orig.jpg")
                .into(new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
            /* Save the bitmap or do something with it here / Ya podemos recoger el bitmap de la imagen leida por Picasso desde el primer parametro de entrada */

                int color = getVibrantColorFromImage(bitmap);//Custom method for load vibrant color from image
                window.setStatusBarColor(color);

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
    public int getVibrantColorFromImage(Bitmap bitmapImage){

        // Synchronous
        Palette p = Palette.from(bitmapImage).generate();
        int vibrantColor = p.getVibrantColor(Color.BLACK); // BLACK iS DEFAULT COLOR IF OTHER FAILS

        /* Asynchronous
        Palette.from(bitmap).generate(new PaletteAsyncListener() {
            public void onGenerated(Palette p) {
            // Use generated instance
            }
        });*/

        return vibrantColor;

    }
}
