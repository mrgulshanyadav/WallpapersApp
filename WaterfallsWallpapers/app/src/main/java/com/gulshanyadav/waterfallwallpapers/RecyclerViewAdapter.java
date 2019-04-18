package com.gulshanyadav.waterfallwallpapers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context c ;
    private ArrayList<Spacecraft> spacecrafts ;
    SharedPreferences sharedpreferences;
    private final int VIEW_TYPE_ITEM=0 , VIEW_TYPE_LOADING=1;
    ILoadMore loadMore;
    boolean isLoading;
    int visibleThreshold=5;
    int lastVisibleItem,totalItemCount;


    public RecyclerViewAdapter(RecyclerView recyclerView,Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = ((GridLayoutManager)recyclerView.getLayoutManager()).getItemCount();
                lastVisibleItem = ((GridLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem+visibleThreshold)){
                    if(loadMore!=null){
                        loadMore.onLoadMore();
                        isLoading = true;
                    }
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return spacecrafts.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_ITEM;
    }

    public void setLoadMore(ILoadMore loadMore) {
        this.loadMore = loadMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_ITEM) {
            View view;
            LayoutInflater mInflater = LayoutInflater.from(c);
            view = mInflater.inflate(R.layout.model, parent, false);
            return new ItemViewHolder(view);
        }
        else if(viewType == VIEW_TYPE_LOADING)
        {
            View view;
            LayoutInflater mInflater = LayoutInflater.from(c);
            view = mInflater.inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof ItemViewHolder){
            MainActivity mainActivity = new MainActivity();
            int height = mainActivity.getScreenHeight(c);
            int width = mainActivity.getScreenWidth(c);

            Spacecraft spacecraft = spacecrafts.get(position);
            ItemViewHolder viewHolder = (ItemViewHolder)holder;
            Picasso.get().load(spacecrafts.get(position).getImage()).resize(width/2,height/2).centerCrop().into(viewHolder.image);
            //viewHolder.image.setImageResource(spacecrafts.get(position).getImage());
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
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
        else if(holder instanceof LoadingViewHolder){
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder)holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }

    }

    public void setLoaded() {
        isLoading = false;
    }

    @Override
    public int getItemCount() {
        if(spacecrafts == null)
            return 0;
        else
            return spacecrafts.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        Context context;
        ImageView image;
        CardView cardView ;

        public ItemViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.model_spacecraftImg);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);

            MainActivity mainActivity = new MainActivity();
            int height = mainActivity.getScreenHeight(c);


            LinearLayout layout = itemView.findViewById(R.id.model_linearLayout);
// Gets the layout params that will allow you to resize the layout
            ViewGroup.LayoutParams params = layout.getLayoutParams();
// Changes the height and width to the specified *pixels*
            params.height = (int) (height*0.4);
            layout.setLayoutParams(params);
        }
    }

    public static class LoadingViewHolder extends RecyclerView.ViewHolder{
        public ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progressBar);
        }
    }


}
