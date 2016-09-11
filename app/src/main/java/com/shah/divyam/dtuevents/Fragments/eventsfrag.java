package com.shah.divyam.dtuevents.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.shah.divyam.dtuevents.R;

import java.util.ArrayList;

public class eventsfrag extends Fragment {


    ArrayList<String>evt_names;
    RecyclerView event_list;


    public eventsfrag() {
        // Required empty public constructor
    }
//    public  boolean isConnectedToNet(){
//
//        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
//        if(networkInfo!=null&&networkInfo.isConnected()){
//            return true;
//        }
//        else{
//            Toast.makeText(getContext(), "Please Connect to Internet", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        evt_names=new ArrayList<>();
        evt_names.add("event1");
        evt_names.add("event2");
        evt_names.add("event3");
        evt_names.add("event4");
        evt_names.add("event5");
        evt_names.add("event6");
        evt_names.add("event7");

        View rootView= inflater.inflate(R.layout.fragment_eventsfrag, container, false);
        event_list=(RecyclerView)rootView.findViewById(R.id.rv_events);
        eventadapter mAdapter=new eventadapter(evt_names);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        event_list.setLayoutManager(layoutManager);

        event_list.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        event_list.setHasFixedSize(true);
        return rootView;
    }


    public class eventviewholder extends RecyclerView.ViewHolder
    {

        TextView tvName;

        public eventviewholder(View itemView) {
            super(itemView);
            tvName= (TextView) itemView.findViewById(R.id.tvEventName);
        }
    }

    public class eventadapter extends RecyclerView.Adapter<eventviewholder>
    {

        ArrayList<String>mlist;

        public eventadapter(ArrayList<String> mlist) {
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

            holder.tvName.setText(mlist.get(position));

        }

        @Override
        public int getItemCount() {
            return mlist.size();
        }
    }


}
