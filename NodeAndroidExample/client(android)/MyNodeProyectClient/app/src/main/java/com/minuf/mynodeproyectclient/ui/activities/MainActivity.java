package com.minuf.mynodeproyectclient.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.minuf.mynodeproyectclient.R;
import com.minuf.mynodeproyectclient.tools.Constants;
import com.minuf.mynodeproyectclient.tools.MySingletonVolley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;

    Boolean raw = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        mTextView = (TextView) findViewById(R.id.tv_text);

        findViewById(R.id.btnRaw).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!raw) {
                    raw = true;
                    processResponse(null);
                }
            }
        });
        findViewById(R.id.btnText).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (raw) {
                    raw = false;
                    processResponse(null);
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllUsers();
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

        String extra = "";

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

        startActivity(new Intent(MainActivity.this, CrudActivity.class).putExtra("ACTION", extra));

        return super.onOptionsItemSelected(item);
    }

    //custom methods
    public JSONObject getAllUsers(){

        // Petición GET
        MySingletonVolley.
                getInstance(this).
                addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.GET,
                                Constants.GET,
                                new Response.Listener<JSONObject>() {

                                    @Override
                                    public void onResponse(JSONObject response) {
                                        processResponse(response);
                                        Log.e("PETICION CORRECTA : ", "OK");
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.e("ERROR : ", "Error Volley: " + error.getMessage());
                                    }
                                }

                        )
                );
        return null;
    }

    JSONArray resp = null;
    public void processResponse(JSONObject response){
        // Procesar la respuesta Json

        if (response != null){
            try {
                resp = response.getJSONArray("users");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if (raw) {
            mTextView.setText(resp.toString());
        }else{
            String users = "\nALL USERS";
            try {
                for (int i = 0; i<resp.length(); i++){
                    users += "\n\nID: "+resp.getJSONObject(i).getString("_id");
                    users += "\nEMAIL: "+resp.getJSONObject(i).getString("email");
                    users += "\nNAME: "+resp.getJSONObject(i).get("first_name");
                    users += "\nTOKEN: "+resp.getJSONObject(i).getString("token");
                }
                mTextView.setText(users);
            } catch (JSONException e) {
                Log.e("ERROR", e.getMessage());
            }
        }

    }
}
