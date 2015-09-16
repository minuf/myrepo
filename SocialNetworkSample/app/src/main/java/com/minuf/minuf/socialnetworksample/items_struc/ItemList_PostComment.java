package com.minuf.minuf.socialnetworksample.items_struc;

import android.widget.ImageView;

/**
 * Created by jorge on 9/09/15.
 */
public class ItemList_PostComment {
    private String img_prof_people_list_URL,
            txt_people_list,
            txt2_people_list;

    public ItemList_PostComment(String img_prof_people_list_URL, String txt_people_list, String txt2_people_list) {
        this.img_prof_people_list_URL = img_prof_people_list_URL;
        this.txt_people_list = txt_people_list;
        this.txt2_people_list = txt2_people_list;
    }

    public String getImg_prof_people_list_URL() {
        return img_prof_people_list_URL;
    }

    public String getTxt_people_list() {
        return txt_people_list;
    }

    public String getTxt2_people_list() {
        return txt2_people_list;
    }

    public void setTxt2_people_list(String txt2_people_list) {
        this.txt2_people_list = txt2_people_list;
    }

    public void setImg_prof_people_list_URL(String img_prof_people_list_URL) {
        this.img_prof_people_list_URL = img_prof_people_list_URL;
    }

    public void setTxt_people_list(String txt_people_list) {
        this.txt_people_list = txt_people_list;
    }
}

