package com.gulshanyadav.waterfallwallpapers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AdView adView;
    ArrayList<Spacecraft> spacecrafts;
    RecyclerViewAdapter myAdapter;
    ArrayList<Integer> imageLoaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,getString(R.string.admob_app_id));  // App Id goes inside initialize
        adView = findViewById(R.id.activity_main_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xfffdf7f7));
        //getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Waterfall Wallpapers</font>"));

        spacecrafts = new ArrayList<>();
        imageLoaction = new ArrayList<>();

        initializeImageLocations();

        first10Images();

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        myAdapter = new RecyclerViewAdapter(myrv,this,spacecrafts);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);

        myAdapter.setLoadMore(new ILoadMore() {
            @Override
            public void onLoadMore() {
                if(spacecrafts.size() < imageLoaction.size()){
                    spacecrafts.add(null);
                    myAdapter.notifyItemInserted(spacecrafts.size()-1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            spacecrafts.remove(spacecrafts.size()-1);
                            myAdapter.notifyItemRemoved(spacecrafts.size());

                            //more data
                            int index = spacecrafts.size();
                            int end = index+10;
                            for(int i=index;i<end;i++){
                                spacecrafts.add(new Spacecraft(String.valueOf(i), imageLoaction.get(i)));
                            }
                            myAdapter.notifyDataSetChanged();
                            myAdapter.setLoaded();
                        }
                    },1500);
                }else{
                    //Toast.makeText(MainActivity.this,"Loaded",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeImageLocations() {
        imageLoaction.add(R.drawable.img0);
        imageLoaction.add(R.drawable.img1);
        imageLoaction.add(R.drawable.img2);
        imageLoaction.add(R.drawable.img3);
        imageLoaction.add(R.drawable.img4);
        imageLoaction.add(R.drawable.img5);
        imageLoaction.add(R.drawable.img6);
        imageLoaction.add(R.drawable.img7);
        imageLoaction.add(R.drawable.img8);
        imageLoaction.add(R.drawable.img9);
        imageLoaction.add(R.drawable.img10);
        imageLoaction.add(R.drawable.img11);
        imageLoaction.add(R.drawable.img12);
        imageLoaction.add(R.drawable.img13);
        imageLoaction.add(R.drawable.img14);
        imageLoaction.add(R.drawable.img15);
        imageLoaction.add(R.drawable.img16);
        imageLoaction.add(R.drawable.img17);
        imageLoaction.add(R.drawable.img18);
        imageLoaction.add(R.drawable.img19);
        imageLoaction.add(R.drawable.img20);
        imageLoaction.add(R.drawable.img21);
        imageLoaction.add(R.drawable.img22);
        imageLoaction.add(R.drawable.img23);
        imageLoaction.add(R.drawable.img24);
        imageLoaction.add(R.drawable.img25);
        imageLoaction.add(R.drawable.img26);
        imageLoaction.add(R.drawable.img27);
        imageLoaction.add(R.drawable.img28);
        imageLoaction.add(R.drawable.img29);
    }

    private void first10Images() {
        for(int i=0;i<10;i++) {
            spacecrafts.add(new Spacecraft(String.valueOf(i), imageLoaction.get(i)));
        }
    }

    public int getScreenHeight(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int height = metrics.heightPixels;

        return height;
    }

    public int getScreenWidth(Context context)
    {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;

        return width;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            final String appPackageName = getPackageName();
            Intent sharingintent = new Intent(android.content.Intent.ACTION_SEND);
            sharingintent.setType("text/plain");
            String shareBody = "Install this simplest GST Calculator "+"https://play.google.com/store/apps/details?id=" + appPackageName;
            sharingintent.putExtra(Intent.EXTRA_SUBJECT,"GST Calculator");
            sharingintent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(sharingintent,"Share via"));
        }
        if (id == R.id.action_rateus) {
            final String appPackageName = getPackageName();
            try{
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            }
            catch(android.content.ActivityNotFoundException anfe)
            {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }

        return super.onOptionsItemSelected(item);
    }

}
