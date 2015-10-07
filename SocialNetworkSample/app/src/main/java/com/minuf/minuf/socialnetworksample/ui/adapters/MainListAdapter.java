package com.minuf.minuf.socialnetworksample.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minuf.minuf.socialnetworksample.R;
import com.minuf.minuf.socialnetworksample.tools.classes.MyApplication_Singleton;
import com.minuf.minuf.socialnetworksample.tools.items_struc.ItemList_PostComment;
import com.minuf.minuf.socialnetworksample.ui.views.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jorge on 9/09/15.
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ItemViewHolder> implements View.OnClickListener{

    private ArrayList<ItemList_PostComment> arrayData;
    private static Context context;

    View itemView;

    //constructor
    public MainListAdapter() {
        //initialize array
        this.arrayData = MyApplication_Singleton.getInstance().getDataList();
    }

    private int lastPosition = -1;
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflate the view and set to holder
        itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.it_comment_list, viewGroup, false);

        itemView.setOnClickListener(this);
        context = viewGroup.getContext();



        ItemViewHolder holder = new ItemViewHolder(itemView);



        return holder;

    }


    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        /**
        //Animation animation = AnimationUtils.loadAnimation(context, (i > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        //Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);

        animation.setDuration(150);
        itemViewHolder.itemView.startAnimation(animation);
        lastPosition = i;*/

        //rescue item from her ArrayList position and bind calling custom method bindItem(item);
        ItemList_PostComment item = arrayData.get(i);




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

        //Holder constructor, initialize views
        public ItemViewHolder(View itemView) {
            super(itemView);
            iv_image = (RoundedImageView)itemView.findViewById(R.id.iv_item_list_comment_prof_photo);
            tv_title = (TextView)itemView.findViewById(R.id.tv_item_list_comment);
            tv_subtitle = (TextView)itemView.findViewById(R.id.tv2_item_ist_comment);
        }
        //custom method for bind view with text content, that can be writted on override method 'onBindViewHolder()', but it only call this method for cleaner code
        public void bindItem(ItemList_PostComment item) {

            tv_title.setText(item.getTxt_people_list());
            tv_subtitle.setText(item.getTxt2_people_list());

            //LIBRERIA PICASSO
            //CON IMAGENES DEMASIADO GRANDES LANZA UN ERROR EN LA CLASE ROUNDEDIMAGEVIEW...algun conflicto al convertir a bitmap
            Picasso.with(context)
                    .load(item.getImg_prof_people_list_URL())
                    .into(iv_image);

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
