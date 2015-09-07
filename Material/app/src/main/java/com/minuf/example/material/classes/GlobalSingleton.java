package com.minuf.example.material.classes;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by jorge on 4/09/15.
 */
public class GlobalSingleton {

    private static GlobalSingleton INSTANCE = null;

    //PRIVATE CONSTRUCTOR SUPRESSES
    private GlobalSingleton(){}

    // creador sincronizado para protegerse de posibles problemas  multi-hilo
    // otra prueba para evitar instanciación múltiple
    private static void createInstance() {
        if (INSTANCE == null) {
            // Sólo se accede a la zona sincronizada
            // cuando la instancia no está creada
            synchronized(GlobalSingleton.class) {
                // En la zona sincronizada sería necesario volver
                // a comprobar que no se ha creado la instancia
                if (INSTANCE == null) {
                    INSTANCE = new GlobalSingleton();
                }
            }
        }
    }
    public static GlobalSingleton getInstance(){
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    //optional
    //El método "clone" es sobreescrito para lanzar una excepción y evitar que el objeto singletone pueda ser "clonado y por lo tanto modificado..":
    /*public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }*/



    ////  RESTO DE METODOS /////
    //     .......

    public void loadImageFromPicasso(Context context, ImageView v){
        Picasso.with(context)
                .load("http://whosbehindmask.weebly.com/uploads/2/8/3/6/28365549/5831103_orig.jpg")    //http://viralandscdn.net/posts/13668/image-sg3SqUON.jpg
                .into(v);
    }
}
