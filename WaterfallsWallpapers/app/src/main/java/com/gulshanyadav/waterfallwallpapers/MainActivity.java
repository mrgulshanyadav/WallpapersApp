package com.gulshanyadav.waterfallwallpapers;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    AdView adView;
    ArrayList<Spacecraft> spacecrafts;
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

        spacecrafts.add(new Spacecraft("0",R.drawable.img0));
        spacecrafts.add(new Spacecraft("1",R.drawable.img1));
        spacecrafts.add(new Spacecraft("2",R.drawable.img2));
        spacecrafts.add(new Spacecraft("3",R.drawable.img3));
        spacecrafts.add(new Spacecraft("4",R.drawable.img4));
        spacecrafts.add(new Spacecraft("5",R.drawable.img5));
        spacecrafts.add(new Spacecraft("6",R.drawable.img6));
        spacecrafts.add(new Spacecraft("7",R.drawable.img7));
        spacecrafts.add(new Spacecraft("8",R.drawable.img8));
        spacecrafts.add(new Spacecraft("9",R.drawable.img9));
        spacecrafts.add(new Spacecraft("10",R.drawable.img10));
        spacecrafts.add(new Spacecraft("11",R.drawable.img11));
        spacecrafts.add(new Spacecraft("12",R.drawable.img12));
        spacecrafts.add(new Spacecraft("13",R.drawable.img13));
        spacecrafts.add(new Spacecraft("14",R.drawable.img14));
        spacecrafts.add(new Spacecraft("15",R.drawable.img15));
        spacecrafts.add(new Spacecraft("16",R.drawable.img16));
        spacecrafts.add(new Spacecraft("17",R.drawable.img17));
        spacecrafts.add(new Spacecraft("18",R.drawable.img18));
        spacecrafts.add(new Spacecraft("19",R.drawable.img19));
        spacecrafts.add(new Spacecraft("20",R.drawable.img20));
        spacecrafts.add(new Spacecraft("21",R.drawable.img21));
        spacecrafts.add(new Spacecraft("22",R.drawable.img22));
        spacecrafts.add(new Spacecraft("23",R.drawable.img23));
        spacecrafts.add(new Spacecraft("24",R.drawable.img24));
        spacecrafts.add(new Spacecraft("25",R.drawable.img25));
        spacecrafts.add(new Spacecraft("26",R.drawable.img26));
        spacecrafts.add(new Spacecraft("27",R.drawable.img27));
        spacecrafts.add(new Spacecraft("28",R.drawable.img28));
        spacecrafts.add(new Spacecraft("29",R.drawable.img29));


        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,spacecrafts);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);

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
