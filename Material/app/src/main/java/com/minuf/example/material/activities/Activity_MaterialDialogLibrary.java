package com.minuf.example.material.activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;
import com.minuf.example.material.R;

public class Activity_MaterialDialogLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__material_dialog_library);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity__material_dialog_library, menu);
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

    public void mStartBasicDialog(View v) {
        final View vv = v;
        new MaterialDialog.Builder(this).callback(new MaterialDialog.ButtonCallback() {
            @Override
            public void onAny(MaterialDialog dialog) {
                super.onAny(dialog);
                Snackbar.make(vv, "No has seleccionado nada", Snackbar.LENGTH_SHORT).show(); //dont do nothing...
            }

            @Override
            public void onPositive(MaterialDialog dialog) {
                super.onPositive(dialog);
                Snackbar.make(vv, "Has seleccionado ACEPTAR", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNegative(MaterialDialog dialog) {
                super.onNegative(dialog);
                Snackbar.make(vv, "Has seleccionado CANCELAR", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onNeutral(MaterialDialog dialog) {
                super.onNeutral(dialog);
                Snackbar.make(vv, "Has seleccionado NEUTRAL", Snackbar.LENGTH_SHORT).show();
            }
        })
                .title("TITULO AQUI")
                .content("Contenido en esta seccion")
                .positiveText("ACEPTAR")
                .negativeText("CANCELAR")
                .neutralText("NEUTRAL")
                .show();
    }
}
