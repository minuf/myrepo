package com.minuf.mysocialnetwork.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.minuf.mysocialnetwork.R;
import com.minuf.mysocialnetwork.tools.Constants;
import com.minuf.mysocialnetwork.tools.MySingletonVolley;
import com.minuf.mysocialnetwork.ui.activities.MainActivity;
import com.minuf.mysocialnetwork.ui.adapters.PostListAdapter;
import com.minuf.mysocialnetwork.ui.items_struc.PostItemStructure;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jorge on 27/10/15.
 */
public class Fragment_Content_Timeline extends Fragment {

    //empty constructor
    public Fragment_Content_Timeline() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //RecyclerView
        RecyclerView list = (RecyclerView)inflater.inflate(R.layout.fragment_content_timeline, container, false);

        callRequest(list);

        return list;
    }

    //custom
    private void callRequest(final RecyclerView list){
        HashMap<String, String> map = new HashMap<>();
        map.put("token", Constants.TOKEN);
        map.put("email", Constants.EMAIL);
        map.put("device_id", Constants.DEVICE_ID);
        map.put("skip", "0");

        JSONObject jObject = new JSONObject(map);
        MySingletonVolley.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constants.GET_POSTS,
                        jObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(final JSONObject response) {
                                //Log.e("RESPONSE", response.toString());

                                do {
                                    Log.e("WAITING","NULL..WAITING RESPONSE");

                                }while (response == null);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        processResponse(response, list);
                                    }
                                }, 710);  //EXAMPLE FOR SOME LAG 710ms

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("ERROR", "Error Volley: " + error.getMessage());
                            }
                        }

                ) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("Accept", "application/json");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8" + getParamsEncoding();
                    }
                }
        );
    }

    private void processResponse(JSONObject jObject, RecyclerView list) {

        Log.e("response", ""+jObject);

        boolean res = false;
        JSONArray jArray = null;

        try {
            res = jObject.getBoolean("res");
            if (res) jArray = jObject.getJSONArray("posts");
            else Log.e("RES", "RES = "+res);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("ERROR JSON", e.getMessage());
        }

        if (res == false) {
            SharedPreferences prefs = getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.remove("EMAIL");
            editor.remove("DEVICE_ID");
            editor.remove("TOKEN");
            editor.commit();

            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
            Toast.makeText(getActivity(), "Error loading posts", Toast.LENGTH_SHORT).show();
        } else {
            if (jArray != null){
                PostListAdapter adapter = new PostListAdapter(jArray);
            }else Toast.makeText(getContext(), "jArray = false", Toast.LENGTH_SHORT).show();
        }

        PostListAdapter adapter = new PostListAdapter(jArray);
        Log.e("JARRAY", ""+jArray);
        list.setAdapter(adapter);

        list.setVisibility(View.VISIBLE);

        //set reciclerview orientation to vertical
        list.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        //set default animations when add or remove item/s from the recyclerview
        list.setItemAnimator(new DefaultItemAnimator());

        //set item decoration, separation between items inside recyclerview
        /** no decoration added */
    }
}
