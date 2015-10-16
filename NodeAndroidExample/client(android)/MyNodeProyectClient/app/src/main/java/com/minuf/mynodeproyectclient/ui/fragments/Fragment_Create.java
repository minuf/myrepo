package com.minuf.mynodeproyectclient.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class Fragment_Create extends Fragment{

    EditText et_email, et_pass, et_name;
    TextView tv_title, tv_response;
    String email, pass, name;


    //empty constructor
    public Fragment_Create() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_form, container, false);

        et_email = (EditText)v.findViewById(R.id.et_email_create);
        et_pass = (EditText)v.findViewById(R.id.et_password_create);
        et_name = (EditText)v.findViewById(R.id.et_name);   et_name.setVisibility(View.VISIBLE);
        tv_title = (TextView)v.findViewById(R.id.tv_form_title);    tv_title.setText("CREATE USER");
        tv_response = (TextView)v.findViewById(R.id.tv_response_create);

        v.findViewById(R.id.btn_create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_email.getText() == null || et_email.getText().toString().length() == 0 || et_email.getText().toString() == "Email") {
                    Snackbar.make(v, "Email mal escrito o vacio..", Snackbar.LENGTH_SHORT).show();
                }else{
                    if (et_pass.getText() == null || et_pass.getText().toString().length() == 0){
                        Snackbar.make(v, "La contraseña no puede estar vacia..", Snackbar.LENGTH_SHORT).show();
                    }else{
                        email = et_email.getText().toString();
                        pass = et_pass.getText().toString();
                        name = et_name.getText().toString();

                        //insertar user (custom method)
                        insertUser(email, pass, name);
                    }
                }
            }
        });

        return v;
    }

    //custom methods

    public void insertUser(String eml, String psw, String first_name) {
        //Mapeo previo de los datos
        HashMap<String, String> map = new HashMap<>();
        map.put("email", eml);
        map.put("password", psw);
        map.put("first_name", first_name);

        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobject = new JSONObject(map);

        // Depurando objeto Json...
        Log.d("OBJETO A GUARDAR: ", jobject.toString());

        // Actualizar datos en el servidor
        MySingletonVolley.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constants.INSERT,
                        jobject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta del servidor

                                tv_response.setText("USUARIO REGISTRADO CON EXITO\n\n"+response.toString());
                                Log.e("PETICION CORRECTA", "REGISTRADO CON EXITO");
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
