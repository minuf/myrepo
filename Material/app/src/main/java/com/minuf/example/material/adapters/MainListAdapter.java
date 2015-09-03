package com.minuf.example.material.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.minuf.example.material.R;
import com.minuf.example.material.items_struc.ItemList1_Structure;
import com.minuf.example.material.views.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jorge on 14/08/15.
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ItemViewHolder> implements View.OnClickListener{

    private ArrayList<ItemList1_Structure> arrayData;
    private static Context context;

    View itemView;

    //constructor
    public MainListAdapter(ArrayList<ItemList1_Structure> arrayData) {
        //initialize array
        this.arrayData = arrayData;
    }

    private int lastPosition = -1;
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflate the view and set to holder
        itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item1_list, viewGroup, false);

        itemView.setOnClickListener(this);
        context = viewGroup.getContext();



        ItemViewHolder holder = new ItemViewHolder(itemView);



        return holder;

    }


    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {

        //Animation animation = AnimationUtils.loadAnimation(context, (i > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        animation.setDuration(150);
        itemViewHolder.itemView.startAnimation(animation);
        lastPosition = i;


        //rescue item from her ArrayList position and bind calling custom method bindItem(item);
        ItemList1_Structure item = arrayData.get(i);




        itemViewHolder.bindItem(item);
    }

    @Override
    public int getItemCount() {
        //get the total of items count
        return arrayData.size();
    }

    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    // subclass for Holder the views (required on ReciclerView)
    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        //create views
        private TextView tv_title, tv_subtitle;
        private RoundedImageView iv_image;
        private ImageView iv_image2;

        //Holder constructor, initialize views
        public ItemViewHolder(View itemView) {
            super(itemView);
            iv_image = (RoundedImageView)itemView.findViewById(R.id.item_photo);
            tv_title = (TextView)itemView.findViewById(R.id.item_title);
            tv_subtitle = (TextView)itemView.findViewById(R.id.item_subtitle);
            iv_image2 = (ImageView)itemView.findViewById(R.id.imageView1);
        }
        //custom method for bind view with text content, that can be writted on override method 'onBindViewHolder()', but it only call this method for cleaner code
        public void bindItem(ItemList1_Structure item) {

            /*Bitmap icon = BitmapFactory.decodeResource(Resources.getSystem(), item.getImage());

            iv_image.setImageBitmap(icon);*/

            iv_image.setImageResource(item.getImage());

            tv_title.setText(item.getTitle());
            tv_subtitle.setText(item.getSubtitle());


            //LIBRERIA PICASSO
            //CON IMAGENES DEMASIADO GRANDES LANZA UN ERROR EN LA CLASE ROUNDEDIMAGEVIEW...algun conflicto al convertir a bitmap
            Picasso.with(context)
                    .load("http://whosbehindmask.weebly.com/uploads/2/8/3/6/28365549/5831103_orig.jpg")    //http://viralandscdn.net/posts/13668/image-sg3SqUON.jpg
                    .into(iv_image2);

            //MUESTRA UN INDICADOR EN LA ESQUINA SUPERIOR IZQUIERDA CON COLOR INDICANDO DE DONDE PROVIENE LA IMAGEN:
            //  ROJO = NETWORK,     AZUL = DISK,    VERDE = MEMORY
            Picasso.with(context).setIndicatorsEnabled(true);

            /***        //EJEMPLO LIBRERIA PICASSO, LEER IMAGEN DE INTERNET, LEER DEL HOLDER, ASIGNAR IMAGEN DE ERROR, REDIMENSIONAR IMAGEN Y ROTAR IMAGEN
            Picasso.with(this)
                    .load("YOUR IMAGE URL HERE")
                    .placeholder(R.drawable.ic_placeholder)   // optional
                    .error(R.drawable.ic_error_fallback)      // optional
                    .resize(250, 200)                        // optional
                    .rotate(90)                             // optional
                    .into(imageView);
             *///
        }
    }
}
