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
public class Fragment_Read extends Fragment {

    EditText et_email, et_pass;
    TextView tv_response, tv_title;
    String email, pass;
    Button btn_read;


    //empty constructor
    public Fragment_Read() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_form, container, false);

        et_email = (EditText)v.findViewById(R.id.et_email_create);
        et_pass = (EditText)v.findViewById(R.id.et_password_create);    et_pass.setVisibility(View.GONE);
        tv_title = (TextView)v.findViewById(R.id.tv_form_title);    tv_title.setText("READ USER");
        tv_response = (TextView)v.findViewById(R.id.tv_response_create);
        btn_read = (Button)v.findViewById(R.id.btn_create); btn_read.setText("READ");

        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_email.getText() == null || et_email.getText().toString().length() == 0 || et_email.getText().toString() == "Email") {
                    Snackbar.make(v, "Email mal escrito o vacio..", Snackbar.LENGTH_SHORT).show();
                } else {
                    email = et_email.getText().toString();

                    readUser(email);
                    //}
                }
            }
        });

        return v;
    }

    //custom methods
    public void readUser(String eml){
        Log.e("PULSADO", "PUL");
        //Mapeo previo de los datos
        HashMap<String, String> map = new HashMap<>();
        map.put("email", eml);
        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobject = new JSONObject(map);

        // Depurando objeto Json...
        Log.e("OBJETO A LEER: ", jobject.toString());
        Log.e("PULSADO", "PUL2");
        // Actualizar datos en el servidor
        MySingletonVolley.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constants.GET_BY_EMAIL,
                        jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta del servidor

                                tv_response.setText("USUARIO ENCONTRADO\n\n"+response.toString());
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
}
