package com.shah.divyam.dtuevents.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by divyam on 11/9/16.
 */
public class NetFetchTask extends AsyncTask<String,Void,Societys.Society> {

   private PostExecuteListener mListener;

    public interface PostExecuteListener{

        public void PostExecuteDone(Societys.Society item);

    }
    public NetFetchTask(PostExecuteListener mListener) {
        super();
        this.mListener = mListener;
    }



    @Override
    protected void onPostExecute(Societys.Society society) {
        super.onPostExecute(society);
        mListener.PostExecuteDone(society);
    }


    @Override
    protected Societys.Society doInBackground(String... params) {
        Societys.Society item = null;
        try{
            URL url = new URL(params[0]);
            HttpURLConnection myConnection = (HttpURLConnection) url.openConnection();
            String resp = getStringResponseFromInputStream(myConnection.getInputStream());
            item = getItemFromJsonString(resp);
            return item;
        }

        catch (MalformedURLException e) {
            //e.printStackTrace();
        } catch (IOException e) {

        } catch (JSONException e) {
            //e.printStackTrace();
        }
        return null;
    }
    private Societys.Society getItemFromJsonString(String resp)throws JSONException{
        Societys.Society item = new Societys.Society();
        JSONObject root = new JSONObject(resp);
        item.title = root.getString("title");
        item.desc = root.getString("Type");
        item.imgurl=root.getString("imageUrl");

        return item;
    }

    private String getStringResponseFromInputStream(InputStream inputStream) throws IOException {
        String resp = null;
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line = r.readLine();
        while(line!=null && !line.isEmpty()){
            sb.append(line);
            line = r.readLine();
        }
        return sb.toString();
    }



}
