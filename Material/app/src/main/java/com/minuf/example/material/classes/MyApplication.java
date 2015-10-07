package com.minuf.example.material.classes;

import android.app.Application;
import android.util.Log;

/**
 * Created by jorge on 9/09/15.
 */
public class MyApplication extends Application {

    private static MyApplication singleton;


    private int numero = 0;


    public static MyApplication getInstance() {
        return singleton;
    }

    //override onCreate, THE FIRST METHOD EXECUTED IN ALL APPLICATION
    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        Log.d("TEST", "Prueba Application");
    }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
