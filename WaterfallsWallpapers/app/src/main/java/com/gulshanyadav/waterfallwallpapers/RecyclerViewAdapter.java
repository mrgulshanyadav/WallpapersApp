package com.gulshanyadav.waterfallwallpapers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context c ;
    private ArrayList<Spacecraft> spacecrafts ;
    SharedPreferences sharedpreferences;


    public RecyclerViewAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(c);
        view = mInflater.inflate(R.layout.model,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.image.setImageResource(spacecrafts.get(position).getImage());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedpreferences = c.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("Id", spacecrafts.get(position).getId());
                editor.commit();

                c.startActivity(new Intent(c, Main2Activity.class));
                ((Activity) c).overridePendingTransition(0, 0);

            }
        });



    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        Context context;
        ImageView image;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = (ImageView)itemView.findViewById(R.id.model_spacecraftImg);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);




        }
    }


}
