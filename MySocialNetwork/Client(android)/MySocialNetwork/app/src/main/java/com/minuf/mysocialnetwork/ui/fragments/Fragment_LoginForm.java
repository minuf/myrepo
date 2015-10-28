package com.minuf.mysocialnetwork.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.minuf.mysocialnetwork.R;
import com.minuf.mysocialnetwork.tools.Constants;
import com.minuf.mysocialnetwork.tools.MySingletonVolley;
import com.minuf.mysocialnetwork.ui.activities.ContentActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jorge on 26/10/15.
 */
public class Fragment_LoginForm extends Fragment{

    EditText et_email, et_pass;
    Button btn_login, btn_signup;

    String email;

    SharedPreferences prefs;

    //empty constructor

    public Fragment_LoginForm(){};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login_form, container, false);

        prefs  = getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        String token = prefs.getString("TOKEN", null);
        String email = prefs.getString("EMAIL", null);

        Log.e("lel", "token: "+token+", email: "+email);

        if (token != null && token!="" && email != null && email!="") {
            Constants.TOKEN = token;
            Constants.EMAIL = email;
            startActivity(new Intent(getActivity(), ContentActivity.class));
            getActivity().finish();
            return v;
        }

        et_email = (EditText)v.findViewById(R.id.et_email);
        et_pass = (EditText)v.findViewById(R.id.et_password);
        btn_login = (Button)v.findViewById(R.id.btn_login);
        btn_signup = (Button)v.findViewById(R.id.btn_signup);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerLayout, new Fragment_SignUpForm(), "SignUp Fragment").addToBackStack("").commit();
            }
        });

        return v;
    }

    //custom methods

    private void login(){
        //get credentials from edit texts
       /* while (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0){
            getActivity().getSupportFragmentManager().popBackStackImmediate();
        }*/
        email = et_email.getText().toString();
        String pass = et_pass.getText().toString();
        //check if are valid values
        if (email!="" && email!=null && !email.isEmpty() && pass!="" && pass!=null && !pass.isEmpty()) {
            //mapeo de los datos
            HashMap<String, String> map = new HashMap<>();
            map.put("email", email);
            map.put("password", pass);
            map.put("device_id", Constants.DEVICE_ID);

            // Crear nuevo objeto Json basado en el mapa
            JSONObject jobject = new JSONObject(map);

            // Depurando objeto Json...
            Log.d("OBJETO A GUARDAR: ", jobject.toString());

            //generando peticion
            generateRequest(jobject);

        }else {
            Toast.makeText(getContext(), "Invalid arguments..", Toast.LENGTH_SHORT).show();
            if (email.isEmpty()) et_email.setError("Invalid or empty email");
            if (pass.isEmpty()) et_pass.setError("Password cant be empty");
        }

    }

    public void generateRequest(JSONObject jObject) {
        MySingletonVolley.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constants.LOGIN,
                        jObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Log.e("RESPONSE", response.toString());
                                processResponse(response);
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

    public void processResponse(JSONObject jObject){

        boolean res;
        String response;
        String token;

        try {
            res = jObject.getBoolean("res");
            response = jObject.getString("response");
            token = jObject.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
            res = false;
            response = "ERROR LOGIN";
            token = null;
        }

        Log.e("RESULT", ""+jObject);

        if (res) {

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("TOKEN", token);
            editor.putString("EMAIL", email);
            editor.putString("DEVICE_ID", Constants.DEVICE_ID);
            editor.commit();

            Constants.EMAIL = email;
            Constants.TOKEN = token;

            startActivity(new Intent(getActivity(), ContentActivity.class));
            getActivity().finish();

        }else {
            Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
        }
    }
}
