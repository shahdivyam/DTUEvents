package com.shah.divyam.dtuevents.Fragments;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shah.divyam.dtuevents.R;
import com.shah.divyam.dtuevents.utils.Societys;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class societyfrag extends Fragment {

    ArrayList<Societys.Society> myList;
    RecyclerView societyList;


    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    public Boolean isOnline() {
        try {

            final Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");

            int returnVal = p1.waitFor();
            return (returnVal == 0);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getActivity().finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_societeyfrag,container,false);
        societyList = (RecyclerView) rootView.findViewById(R.id.rv_society_list);

        if(!isNetworkAvailable() || !isOnline())
        {
               showAlert();

        }
        else {

            try {

                Societys societys = new Societys();
                myList = societys.getSocietyList();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            if (myList != null) {
                SocietyAdapter mAdapter = new SocietyAdapter(myList);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

                societyList.setLayoutManager(layoutManager);
                societyList.setAdapter(mAdapter);

                mAdapter.notifyDataSetChanged();

                societyList.setHasFixedSize(true);
            }
        }
        return rootView;
    }
    public class SocietyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView desc;
        ImageView banner;

        public SocietyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.rv_tv_title);
            desc = (TextView) itemView.findViewById(R.id.rv_tv_soc_desc);
            banner = (ImageView) itemView.findViewById(R.id.rv_img_soc_banner);

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
            holder.desc.setText(entry.desc);

            Picasso.with(getContext()).load(entry.imgurl).resize(holder.banner.getWidth(),350).into(holder.banner);
            holder.banner.setScaleType(ImageView.ScaleType.CENTER_CROP);

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

    }





}
