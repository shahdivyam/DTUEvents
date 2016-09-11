package com.shah.divyam.dtuevents.utils;

import android.widget.ImageView;

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
    public static int count = 1;
    public Societys() {

        SocietyList = new ArrayList<>();
        jsonUrlList.add("https://api.myjson.com/bins/1qvaa");
        jsonUrlList.add("https://api.myjson.com/bins/3liz6");
        jsonUrlList.add("https://api.myjson.com/bins/1c2vm");
        jsonUrlList.add("https://api.myjson.com/bins/4de2a");
        jsonUrlList.add("https://api.myjson.com/bins/50ytu");
        jsonUrlList.add("https://api.myjson.com/bins/3at6a");

    }

    public ArrayList<Society> getSocietyList() throws ExecutionException, InterruptedException {

        if(societyfrag.isConnectedToNet()==true) {

            for (int i = 0; i < jsonUrlList.size(); i++) {
                String url = jsonUrlList.get(i);
                Society item;
                NetFetchTask nft = new NetFetchTask(new NetFetchTask.PostExecuteListener() {
                    @Override
                    public void PostExecuteDone(Society item) {

                    }
                });
                item = nft.execute(url).get();
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

        public Society(String title) {
            this.title = title;
        }

        public Society(String title, ImageView img, String desc) {
            this.title = title;
            this.image = img;
            this.desc = desc;
        }
        int id;
        String title;
        ImageView image;
        String desc;
        int likes;
        int bookmarks;
    }

}
