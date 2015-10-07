package com.minuf.minuf.socialnetworksample.tools.items_struc;

import java.util.ArrayList;

/**
 * Created by jorge on 11/09/15.
 */
public class ItemList_Post {
    private String user_prof_image_url, user_prof_name, user_comment, image_post_url, image_title;
    private ArrayList<ItemList_PostComment> comment_list;

    public ItemList_Post(String user_prof_image_url, String user_prof_name, String user_comment,
                         String image_post_url, String image_title, ArrayList<ItemList_PostComment> comment_list) {
        this.user_prof_image_url = user_prof_image_url;
        this.user_prof_name = user_prof_name;
        this.user_comment = user_comment;
        this.image_post_url = image_post_url;
        this.image_title = image_title;
        this.comment_list = comment_list;
    }

    public String getUser_prof_image_url() {
        return user_prof_image_url;
    }

    public String getUser_prof_name() {
        return user_prof_name;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public String getImage_post_url() {
        return image_post_url;
    }

    public String getImage_title() {
        return image_title;
    }

    public ArrayList<ItemList_PostComment> getComment_list() {
        return comment_list;
    }


}
