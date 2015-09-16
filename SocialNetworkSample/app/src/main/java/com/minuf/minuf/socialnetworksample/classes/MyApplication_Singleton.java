package com.minuf.minuf.socialnetworksample.classes;

import android.app.Application;
import android.util.Log;

import com.minuf.minuf.socialnetworksample.items_struc.ItemList_PostComment;
import com.minuf.minuf.socialnetworksample.items_struc.ItemList_Post;

import java.util.ArrayList;

/**
 * Created by jorge on 9/09/15.
 */
public class MyApplication_Singleton extends Application {

    private static MyApplication_Singleton singleton;

    private ArrayList<ItemList_PostComment> dataList = new ArrayList<>();
    private ArrayList<ItemList_Post> postDataList = new ArrayList<>();
    private ArrayList<ItemList_PostComment> comment_list = new ArrayList<>();

    public static MyApplication_Singleton getInstance() {
        return singleton;
    }

    //override onCreate, THE FIRST METHOD EXECUTED IN ALL APPLICATION
    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        readData();
        readDataPostList();
        Log.e("TEST", "DATA READED FROM APPLICATION SINGLETON CLASS");
    }

    private void readData(){
        for (int i=0; i<5; i++) {
            dataList.add(new ItemList_PostComment("http://diferenciaentre.info/wp-content/uploads/2015/07/selfie.jpg", "Name", "Comment"));
            dataList.add(new ItemList_PostComment("http://blog.dictionary.com/wp-content/uploads/2013/11/selfie_big.jpg", "Name", "Comment"));
            dataList.add(new ItemList_PostComment("http://whosbehindmask.weebly.com/uploads/2/8/3/6/28365549/5831103_orig.jpg", "Name", "Comment"));
            dataList.add(new ItemList_PostComment("http://worldnewsdailyreport.com/wp-content/uploads/2014/08/shark-selfie.png", "Name", "Comment"));
            dataList.add(new ItemList_PostComment("http://cdn1.medicalnewstoday.com/content/images/articles/287/287757/man-taking-selfie.jpg", "Name", "Comment"));
            dataList.add(new ItemList_PostComment("http://time-az.com/images/2014/01/20140118RoyReid.ca-Breakfast-Beauty-Selfie-10.jpg", "Name", "Comment"));
            dataList.add(new ItemList_PostComment("http://i.telegraph.co.uk/multimedia/archive/02858/man_2858382b.jpg", "Name", "Comment"));
            dataList.add(new ItemList_PostComment("http://www.grandselfie.com/images/profile_pics/selfie-mondays.jpg", "Name", "Comment"));
            dataList.add(new ItemList_PostComment("https://crearcomunidad.files.wordpress.com/2013/11/selfiesex.jpg", "Name", "Comment"));
            dataList.add(new ItemList_PostComment("http://edgecast.buscafs.com/www.levelup.com/public/uploads/images/344684.jpg", "Name", "Comment"));
        }
    }

    private void readDataPostList() {

        comment_list.add(dataList.get(0));
        comment_list.add(dataList.get(1));
        comment_list.add(dataList.get(2));

        postDataList.add(new ItemList_Post("http://diferenciaentre.info/wp-content/uploads/2015/07/selfie.jpg",
                "Name", "Comment", "http://diferenciaentre.info/wp-content/uploads/2015/07/selfie.jpg",
                "Image Title", comment_list));
        postDataList.add(new ItemList_Post("http://blog.dictionary.com/wp-content/uploads/2013/11/selfie_big.jpg",
                "Name", "Comment", "http://blog.dictionary.com/wp-content/uploads/2013/11/selfie_big.jpg",
                "Image Title", comment_list));
        postDataList.add(new ItemList_Post("http://whosbehindmask.weebly.com/uploads/2/8/3/6/28365549/5831103_orig.jpg",
                "Name", "Comment", "http://whosbehindmask.weebly.com/uploads/2/8/3/6/28365549/5831103_orig.jpg",
                "Image Title", comment_list));
        postDataList.add(new ItemList_Post("http://cdn1.medicalnewstoday.com/content/images/articles/287/287757/man-taking-selfie.jpg",
                "Name", "Comment", "http://cdn1.medicalnewstoday.com/content/images/articles/287/287757/man-taking-selfie.jpg",
                "Image Title", comment_list));
        postDataList.add(new ItemList_Post("http://www.grandselfie.com/images/profile_pics/selfie-mondays.jpg",
                "Name", "Comment", "http://www.grandselfie.com/images/profile_pics/selfie-mondays.jpg",
                "Image Title", comment_list));

    }

    public ArrayList<ItemList_PostComment> getDataList() {

        return dataList;
    }

    public ArrayList<ItemList_Post> getPostDataList() {
        return postDataList;
    }

    public ArrayList<ItemList_PostComment> getPostCommentList() {
        return comment_list;
    }


}
