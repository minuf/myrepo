package com.minuf.mynodeproyectclient.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.minuf.mynodeproyectclient.R;
import com.minuf.mynodeproyectclient.tools.Constants;
import com.minuf.mynodeproyectclient.tools.MySingletonVolley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jorge on 15/10/15.
 */
public class Fragment_Delete extends Fragment {

    EditText et_email, et_pass;
    TextView tv_response, tv_title;
    String email, pass;
    Button btn_read;


    //empty constructor
    public Fragment_Delete() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_form, container, false);

        et_email = (EditText)v.findViewById(R.id.et_email_create);
        et_pass = (EditText)v.findViewById(R.id.et_password_create);    et_pass.setVisibility(View.GONE);
        tv_title = (TextView)v.findViewById(R.id.tv_form_title);    tv_title.setText("DELETE USER");
        tv_response = (TextView)v.findViewById(R.id.tv_response_create);
        btn_read = (Button)v.findViewById(R.id.btn_create); btn_read.setText("DELETE");

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_email.getText() == null || et_email.getText().toString().length() == 0 || et_email.getText().toString() == "Email") {
                    Snackbar.make(v, "Email mal escrito o vacio..", Snackbar.LENGTH_SHORT).show();
                } else {
                    email = et_email.getText().toString();

                    deleteUser(email);
                    //}
                }
            }
        });

        return v;
    }

    //custom methods
    public void deleteUser(String eml){

        // Depurando
        Log.e("USUARIO A BORRAR: ", eml);
        // Actualizar datos en el servidor
        MySingletonVolley.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.DELETE,
                        Constants.DELETE+"/"+eml,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta del servidor

                                tv_response.setText("USUARIO ELIMINADO\n\n"+response.toString());
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("ERROR", "Error Volley: " + error.getMessage());
                            }
                        }

                )
        );
    }
}
