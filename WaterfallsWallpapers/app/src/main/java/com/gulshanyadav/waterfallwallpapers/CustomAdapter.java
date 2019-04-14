package com.gulshanyadav.waterfallwallpapers;

/*
This project is developed by Gulshan Yadav and is copyrighted to use without permission.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter{
    Context c;
    ArrayList<Spacecraft> spacecrafts;
    SharedPreferences sharedpreferences;
    Context context;

    public CustomAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;

    }



    @Override
    public int getCount() {
        return spacecrafts.size();
    }


        @Override
        public Object getItem(int i) {
            return spacecrafts.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null)
            {
                view= LayoutInflater.from(c).inflate(R.layout.model,viewGroup,false);
            }

        final Spacecraft s= (Spacecraft) this.getItem(i);
        ImageView img= view.findViewById(R.id.model_spacecraftImg);

        //BIND
        img.setImageResource(s.getImage());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedpreferences = c.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Id", s.getId());
                editor.commit();

                c.startActivity(new Intent(c, Main2Activity.class));
                ((Activity) c).overridePendingTransition(0, 0);
            }
        });

        return view;
    }

}