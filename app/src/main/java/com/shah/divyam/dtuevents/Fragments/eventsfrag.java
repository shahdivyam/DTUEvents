package com.shah.divyam.dtuevents.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.shah.divyam.dtuevents.R;
import com.shah.divyam.dtuevents.model.Event;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class eventsfrag extends Fragment {


    ArrayList<String>evt_names;
    RecyclerView event_list;
    ArrayList<Event>ev_list;
    ArrayList<String>ev_url;
    RequestQueue queue;


    public eventsfrag() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ev_list=new ArrayList<>();
        ev_url=new ArrayList<>();
        queue= Volley.newRequestQueue(getContext());
       ev_url.add("https://api.myjson.com/bins/4zkr2");
        ev_url.add("https://api.myjson.com/bins/4zkr2");
        ev_url.add("https://api.myjson.com/bins/4zkr2");
        ev_url.add("https://api.myjson.com/bins/4zkr2");
        ev_url.add("https://api.myjson.com/bins/4zkr2");
        ev_url.add("https://api.myjson.com/bins/4zkr2");
        ev_url.add("https://api.myjson.com/bins/4zkr2");
        queue.start();
       final eventadapter mAdapter=new eventadapter(ev_list);

        for(int i=0;i<ev_url.size();i++) {

            String url = ev_url.get(i);
            JsonObjectRequest req=new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Event n=new Event();
                    try {
                        n.setEvname(response.getString("title"));
                        n.setImgurl(response.getString("imageUrl"));
                        n.setOrgan(response.getString("Organby"));
                        n.setOn(response.getString("date"));
                        ev_list.add(n);
                        mAdapter.notifyDataSetChanged();
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }

                }
            },new Response.ErrorListener()
            {

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        queue.add(req);
        }




        View rootView= inflater.inflate(R.layout.fragment_eventsfrag, container, false);
        event_list=(RecyclerView)rootView.findViewById(R.id.rv_events);








        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        event_list.setLayoutManager(layoutManager);

        event_list.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        event_list.setHasFixedSize(true);
        return rootView;
    }


    public class eventviewholder extends RecyclerView.ViewHolder
    {

        ImageView banner;
        TextView tvName;
        TextView tvorgan;
        TextView tvon;

        public eventviewholder(View itemView) {
            super(itemView);
            tvName= (TextView) itemView.findViewById(R.id.tvEventName);
            tvorgan= (TextView) itemView.findViewById(R.id.tvEventorg);
            tvon= (TextView) itemView.findViewById(R.id.tvEventdate);

            banner=(ImageView)itemView.findViewById(R.id.imBanner);
        }
    }

    public class eventadapter extends RecyclerView.Adapter<eventviewholder>
    {

        ArrayList<Event>mlist;

        public eventadapter(ArrayList<Event> mlist) {
            this.mlist = mlist;
        }

        @Override
        public eventviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(parent.getContext());
            View itemview;
            eventviewholder viewHolder;
            itemview=inflater.inflate(R.layout.eventcard,parent,false);
            viewHolder=new eventviewholder(itemview);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(eventviewholder holder, int position) {

            holder.tvName.setText(mlist.get(position).getEvname());
            holder.tvorgan.setText(mlist.get(position).getOrgan());
            holder.tvon.setText(mlist.get(position).getOn());
            Picasso.with(getContext()).load(mlist.get(position).getImgurl()).into(holder.banner);

        }

        @Override
        public int getItemCount() {
            return mlist.size();
        }
    }


}
