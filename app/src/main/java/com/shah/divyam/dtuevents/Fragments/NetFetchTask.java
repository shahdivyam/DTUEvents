package com.shah.divyam.dtuevents.Fragments;

import android.os.AsyncTask;

/**
 * Created by divyam on 11/9/16.
 */
public class NetFetchTask extends AsyncTask<String,Void,Societys.Society> {

   private PostExecuteListener mListener;

    public interface PostExecuteListener{

        public void PostExecuteDone(Items.Item item);

    }

    @Override
    protected Societys.Society doInBackground(String... params) {
        return null;
    }
}
