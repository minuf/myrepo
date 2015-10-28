package com.minuf.mysocialnetwork.ui.adapters;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minuf.mysocialnetwork.R;
import com.minuf.mysocialnetwork.ui.items_struc.PostItemStructure;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jorge on 27/10/15.
 */
public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostViewHolder> {

    private JSONArray arrayData;

    static Context context;

    public PostListAdapter(JSONArray data) {this.arrayData = data;}

    //...

    public static class PostViewHolder
            extends RecyclerView.ViewHolder {

        private TextView tv_user_comment;
        private Toolbar tbCard;
        private CardView cv_card;
        private ImageView iv_image;

        public PostViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            tbCard= (Toolbar)itemView.findViewById(R.id.TbCard);
            tv_user_comment = (TextView)itemView.findViewById(R.id.tv_postUserComment);
            iv_image = (ImageView)itemView.findViewById(R.id.iv_post_item);
        }

        public void bindTitular(JSONObject j) {
            String comment = "";
            String url = "";

            try {
                comment = j.getString("user_comment");
                url = j.getString("post_url");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            tbCard.setTitle("like, comment..");

            Picasso.with(context).load(url).placeholder(R.drawable.picasso_wait).into(iv_image);
            /*tbCard.setOnMenuItemClickListener(
                    new Toolbar.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()) {
                                case R.id.action_1:
                                    Snackbar.make(cv_card, "Tarjeta: " + count + ". Accion 1", Snackbar.LENGTH_SHORT).show();
                                    Log.i("Toolbar 2", "Acción Tarjeta 1");
                                    break;
                                case R.id.action_2:
                                    Snackbar.make(cv_card, "Tarjeta: "+count+". Accion 1", Snackbar.LENGTH_SHORT).show();
                                    Log.i("Toolbar 2", "Acción Tarjeta 2");
                                    break;
                            }

                            return true;
                        }
                    });
            tbCard.getMenu().clear();*/

            //tbCard.inflateMenu(R.menu.menu_tarjeta);



        }
    }

    //...


    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        //context = viewGroup.getContext();

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.struc_post_list, viewGroup, false);

        PostViewHolder tvh = new PostViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(PostViewHolder viewHolder, int pos) {
        JSONObject item = null;
        try {
            item = arrayData.getJSONObject(pos);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        viewHolder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return arrayData.length();
    }
}
