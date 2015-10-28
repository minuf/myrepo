package com.minuf.mysocialnetwork.ui.items_struc;

import android.widget.ImageView;
import android.widget.VideoView;

import java.util.Date;

/**
 * Created by jorge on 27/10/15.
 */
public class PostItemStructure {

    private String id_post, type, url, user_comment, id_user, v;
    private Date created;


    //constructor for post with image
    public PostItemStructure(String id_post, String type, String url, String user_comment, String id_user, String v, Date created) {
        this.id_post = id_post;
        this.type = type;
        this.url = url;
        this.user_comment = user_comment;
        this.id_user = id_user;
        this.v = v;
        this.created = created;
    }


    public String getId_post() {
        return id_post;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public String getId_user() {
        return id_user;
    }

    public String getV() {
        return v;
    }

    public Date getCreated() {
        return created;
    }

}
