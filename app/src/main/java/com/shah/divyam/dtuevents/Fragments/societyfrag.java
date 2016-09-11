package com.shah.divyam.dtuevents.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shah.divyam.dtuevents.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class societyfrag extends Fragment {

    ArrayList<Societys.Society> myList;
    RecyclerView societyList;

    public societyfrag() {
        // Required empty public constructor
        Societys societys = new Societys();
        myList = societys.getSocietyList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_societeyfrag,container,false);
        societyList = (RecyclerView) rootView.findViewById(R.id.rv_society_list);
        SocietyAdapter mAdapter = new SocietyAdapter(myList);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        societyList.setLayoutManager(layoutManager);

        societyList.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        societyList.setHasFixedSize(true);

        return rootView;
    }
    public class SocietyViewHolder extends RecyclerView.ViewHolder{

        TextView title;

        public SocietyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.rv_tv_title);
        }
    }
    public class SocietyAdapter extends RecyclerView.Adapter<SocietyViewHolder>{

        ArrayList<Societys.Society>mList;

        public SocietyAdapter(ArrayList<Societys.Society> mList) {
            this.mList = mList;
        }

        @Override
        public SocietyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.society_card,parent,false);

            return new SocietyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(SocietyViewHolder holder, int position) {
            Societys.Society entry = mList.get(position);
            holder.title.setText(entry.title);
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }


}
