package com.minuf.mysocialnetwork.ui.activities;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.minuf.mysocialnetwork.R;
import com.minuf.mysocialnetwork.tools.Constants;
import com.minuf.mysocialnetwork.ui.fragments.Fragment_LoginForm;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        Constants.DEVICE_ID = android_id;

        Log.e("Android", "Android ID : " + Constants.DEVICE_ID);

        getSupportFragmentManager().beginTransaction().add(R.id.containerLayout, new Fragment_LoginForm(), "Login Form").commit();
    }

}
