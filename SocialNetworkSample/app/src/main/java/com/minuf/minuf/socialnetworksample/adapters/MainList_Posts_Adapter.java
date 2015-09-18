package com.minuf.minuf.socialnetworksample.adapters;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minuf.minuf.socialnetworksample.R;
import com.minuf.minuf.socialnetworksample.anim_deco.DividerItemDecoration;
import com.minuf.minuf.socialnetworksample.classes.MyApplication_Singleton;
import com.minuf.minuf.socialnetworksample.items_struc.ItemList_Post;
import com.minuf.minuf.socialnetworksample.views.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jorge on 11/09/15.
 */
public class MainList_Posts_Adapter extends RecyclerView.Adapter<MainList_Posts_Adapter.ItemViewHolder> implements View.OnClickListener{

    private ArrayList<ItemList_Post> arrayData;
    private static Context context;

    View itemView;

    //constructor
    public MainList_Posts_Adapter() {
        //initialize array
        this.arrayData = MyApplication_Singleton.getInstance().getPostDataList();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //inflate the view and set to holder
        itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.it_post_card_mainlist, viewGroup, false);

        itemView.setOnClickListener(this);
        context = viewGroup.getContext();



        ItemViewHolder holder = new ItemViewHolder(itemView);



        return holder;

    }


    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {

        //get item from her ArrayList position and bind calling custom method bindItem(item);
        ItemList_Post item = arrayData.get(i);

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
        private RoundedImageView iv_img_userProf_post;
        private TextView tv_userName, tv_userComment;
        private ImageView iv_mainPost, iv_btn_like, iv_btn_comment;
        private Toolbar tb_postOptions;
        private CardView cv_Post;
        private RecyclerView rv_comments_post;
        private LinearLayout lay, lay_user_info;
        private int itemHeight;



        //Holder constructor, initialize views
        public ItemViewHolder(View itemView) {
            super(itemView);
            iv_img_userProf_post = (RoundedImageView)itemView.findViewById(R.id.iv_item_user_profimg_post);
            tv_userName = (TextView)itemView.findViewById(R.id.tv_item_user_profname_post);
            tv_userComment = (TextView)itemView.findViewById(R.id.tv2_item_user_profcomm_post);
            iv_mainPost = (ImageView)itemView.findViewById(R.id.iv_main_img_post);
            tb_postOptions = (Toolbar)itemView.findViewById(R.id.tb_options_card_post);
            rv_comments_post = (RecyclerView)itemView.findViewById(R.id.list_comment_post_only2item);
            lay = (LinearLayout)itemView.findViewById(R.id.ll_postList_comments);
            cv_Post = (CardView)itemView.findViewById(R.id.cardViewPostMain);
            lay_user_info = (LinearLayout)itemView.findViewById(R.id.lay_user_info_post);
            iv_btn_like = (ImageView)itemView.findViewById(R.id.iv_btn_like);
            iv_btn_comment = (ImageView)itemView.findViewById(R.id.iv_btn_comment);
        }
        //custom method for bind view with text content, that can be writted on override method 'onBindViewHolder()', but it only call this method for cleaner code
        ValueAnimator mAnimator;
        public void bindItem(ItemList_Post item) {

            //cv_Post.setBottom(800);

            Picasso.with(context)
                    .load(item.getUser_prof_image_url())
                    .into(iv_img_userProf_post);
            tv_userName.setText(item.getUser_prof_name());
            tv_userComment.setText(item.getUser_comment());
            Picasso.with(context)
                    .load(item.getImage_post_url())
                    .into(iv_mainPost);
            tb_postOptions.getMenu().clear();
            tb_postOptions.inflateMenu(R.menu.menu_tarjeta);


            iv_btn_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(iv_btn_like, "LIKED", Snackbar.LENGTH_SHORT).show();
                }
            });
            iv_btn_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lay.getVisibility() == View.GONE) {
                        expand();
                    } else {
                        collapse();
                    }
                }
            });

            //Add onPreDrawListener
            lay.getViewTreeObserver().addOnPreDrawListener(
                    new ViewTreeObserver.OnPreDrawListener() {

                        @Override
                        public boolean onPreDraw() {
                            lay.getViewTreeObserver().removeOnPreDrawListener(this);
                            lay.setVisibility(View.GONE);

                            final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                            final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                            lay.measure(widthSpec, heightSpec);

                            mAnimator = slideAnimator(0, lay.getMeasuredHeight());

                            return true;
                        }
                    });

            //CREATE ADAPTER, SETS ONCLICK AND SETS TO LIST
            MainListAdapter adapter = new MainListAdapter();
            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Pulsado el item: " + (1 + rv_comments_post.getChildAdapterPosition(v)), Snackbar.LENGTH_SHORT).show();
                }
            });
            //rv_comments_post.setAdapter(adapter);
            rv_comments_post.swapAdapter(adapter, true);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams)rv_comments_post.getLayoutParams();
            itemHeight = (lay_user_info.getLayoutParams().height)*8;
            params.height = itemHeight;
            rv_comments_post.setLayoutParams(params);
            rv_comments_post.setHasFixedSize(true);

            //sets the layout manager, decoration and animation for correcty implementation of recyclerview ( recycler require that)
            rv_comments_post.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            rv_comments_post.setItemAnimator(new DefaultItemAnimator());
            rv_comments_post.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));




            Picasso.with(context).setIndicatorsEnabled(true);


        }

        private void expand() {
            //set Visible
            lay.setVisibility(View.VISIBLE);

            mAnimator.start();
        }

        private void collapse() {
            int finalHeight = lay.getHeight();

            ValueAnimator mAnimator = slideAnimator(finalHeight, 0);

            mAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animator) {
                    //Height=0, but it set visibility to GONE
                    lay.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationStart(Animator animator) {
                }

                @Override
                public void onAnimationCancel(Animator animator) {
                }

                @Override
                public void onAnimationRepeat(Animator animator) {
                }
            });
            mAnimator.start();
        }

        private ValueAnimator slideAnimator(int start, int end) {

            ValueAnimator animator = ValueAnimator.ofInt(start, end);


            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    //Update Height
                    int value = (Integer) valueAnimator.getAnimatedValue();

                    ViewGroup.LayoutParams layoutParams = lay.getLayoutParams();
                    layoutParams.height = value;
                    lay.setLayoutParams(layoutParams);
                }
            });
            return animator;
        }
    }
}
