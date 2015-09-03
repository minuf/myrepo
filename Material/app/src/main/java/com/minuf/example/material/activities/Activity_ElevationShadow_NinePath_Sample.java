package com.minuf.example.material.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.h6ah4i.android.materialshadowninepatch.MaterialShadowContainerView;
import com.minuf.example.material.R;

public class Activity_ElevationShadow_NinePath_Sample extends AppCompatActivity {

    private static final int SEEKBAR_MAX = 1000;
    private static final float MAX_TRANSLATION_Z = 9;

    float mDisplayDensity;

    SeekBar mSeekBarElevation;
    TextView tv_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__elevation_shadow__nine_path__sample);

        final MaterialShadowContainerView shadowView = (MaterialShadowContainerView)
                findViewById(R.id.shadow_item_container);
        final MaterialShadowContainerView shadowView2 = (MaterialShadowContainerView)
                findViewById(R.id.shadow_item_container2);

        mDisplayDensity = getResources().getDisplayMetrics().density;

        tv_progress = (TextView)findViewById(R.id.tv_currentelevation);

        mSeekBarElevation = (SeekBar)findViewById(R.id.seekBar);
        mSeekBarElevation.setMax(SEEKBAR_MAX);
        mSeekBarElevation.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_progress.setText("" + progress / 100);

                switch (seekBar.getId()) {
                    case R.id.seekBar:
                        if (fromUser) {
                            shadowView.setShadowTranslationZ(progressToTranslationZAmount(progress));
                            shadowView2.setShadowElevation(progressToTranslationZAmount(progress));
                        }
                        break;
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__elevation_shadow__nine_path__sample, menu);
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

    private float progressToTranslationZAmount(int progress) {
        return MAX_TRANSLATION_Z * mDisplayDensity * progress / SEEKBAR_MAX;
    }
}
