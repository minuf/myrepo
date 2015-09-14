package com.minuf.example.material.activities;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.minuf.example.material.R;
import com.squareup.picasso.Picasso;
public class Activity_FullScreenPhoto extends AppCompatActivity {

    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__full_screen_photo);

        ImageView iv_photo = (ImageView)findViewById(R.id.imageView_fullscreen);

        fab = (FloatingActionButton)findViewById(R.id.btnFabToCardViewAvtivity);

        Picasso.with(this)
                .load("http://whosbehindmask.weebly.com/uploads/2/8/3/6/28365549/5831103_orig.jpg")    //http://viralandscdn.net/posts/13668/image-sg3SqUON.jpg
                .into(iv_photo);

        nextActivityWithSharedElement();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__full_screen_photo, menu);
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

    public void nextActivityWithSharedElement() {
        final Intent intent = new Intent(Activity_FullScreenPhoto.this, Activity_CardViewPhoto.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fab.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    /*ActivityOptions options = ActivityOptions
                            .makeSceneTransitionAnimation(Activity_FullScreenPhoto.this, v, v.getTransitionName());*/

                    startActivity(intent);
                }
            });
        } else {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
        }
    }
}
