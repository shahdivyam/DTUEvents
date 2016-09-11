package com.shah.divyam.dtuevents.Fragments;

import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by divyam on 10/9/16.
 */
public class Societys {

    public  ArrayList<Society> SocietyList;
    public ArrayList<String> jsonUrlList;

    public Societys() {

        SocietyList = new ArrayList<>();
        jsonUrlList.add("https://api.myjson.com/bins/1qvaa");
        jsonUrlList.add("https://api.myjson.com/bins/3liz6");
        jsonUrlList.add("https://api.myjson.com/bins/1c2vm");
        jsonUrlList.add("https://api.myjson.com/bins/4de2a");
        jsonUrlList.add("https://api.myjson.com/bins/50ytu");
        jsonUrlList.add("https://api.myjson.com/bins/3at6a");

    }

    public ArrayList<Society> getSocietyList(){
        SocietyList.add(new Society("IEEE"));
        SocietyList.add(new Society("Enactus"));
        SocietyList.add(new Society("E-Cell"));
        SocietyList.add(new Society("SSE"));
        SocietyList.add(new Society("Engi Fest"));

        return SocietyList;
    }

    public  class Society{
        public Society(String title) {
            this.title = title;
        }

        public Society(String title, String imageURL, String desc) {
            this.title = title;
            this.imageURL = imageURL;
            this.desc = desc;
        }

        String title;
        String imageURL;
        String desc;
        int likes;
        int bookmark;
    }

}
