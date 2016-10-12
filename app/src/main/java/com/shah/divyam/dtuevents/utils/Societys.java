package com.shah.divyam.dtuevents.utils;

import android.util.Log;
import android.widget.ImageView;

import com.shah.divyam.dtuevents.Fragments.eventsfrag;
import com.shah.divyam.dtuevents.Fragments.societyfrag;
import com.shah.divyam.dtuevents.utils.NetFetchTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by divyam on 10/9/16.
 */
public class Societys {

    public  ArrayList<Society> SocietyList;
    public ArrayList<String> jsonUrlList;
    public Society item;
    public static int count = 1;
    eventsfrag frag;
    public Societys() throws ExecutionException, InterruptedException {

        SocietyList = new ArrayList<>();
        jsonUrlList=new ArrayList<>();
        frag = new eventsfrag();

        jsonUrlList.add("https://api.myjson.com/bins/1qvaa");
        jsonUrlList.add("https://api.myjson.com/bins/3liz6");
        jsonUrlList.add("https://api.myjson.com/bins/1c2vm");
        jsonUrlList.add("https://api.myjson.com/bins/4de2a");
        jsonUrlList.add("https://api.myjson.com/bins/50ytu");
        jsonUrlList.add("https://api.myjson.com/bins/3at6a");

    }

    public ArrayList<Society> getSocietyList() throws ExecutionException, InterruptedException {


        if( true) {

            for (int i = 0; i < jsonUrlList.size(); i++) {

                String url = jsonUrlList.get(i);
                new NetFetchTask(new NetFetchTask.PostExecuteListener() {
                    @Override
                    public void PostExecuteDone(Society Item) {
                        item = Item;

                    }
                }).execute(url);

                Log.d("Item"," i :"+i);


                SocietyList.add(item);
            }
            return SocietyList;
        }

        else {
            return null;
        }


    }

    public static class Society{

        public Society() {
        }

        int id;
        public String title;
        public  String imgurl;
        public String desc;
        int likes;
        int bookmarks;
    }

}
