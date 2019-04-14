package com.gulshanyadav.waterfallwallpapers;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gv;
    CustomAdapter adapter;
    AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this,getString(R.string.admob_app_id));  // App Id goes inside initialize
        adView = findViewById(R.id.activity_main_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff000000));

        gv= findViewById(R.id.activity_main_gv);

        adapter=new CustomAdapter(this,getData());
        gv.setAdapter(adapter);

    }

    private ArrayList getData()
    {
        ArrayList<Spacecraft> spacecrafts=new ArrayList<>();
        Spacecraft s=new Spacecraft();
        s=new Spacecraft();
        s.setId("0");
        s.setImage(R.drawable.img0);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("1");
        s.setImage(R.drawable.img1);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("2");
        s.setImage(R.drawable.img2);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("3");
        s.setImage(R.drawable.img3);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("4");
        s.setImage(R.drawable.img4);
        spacecrafts.add(s);

        return spacecrafts;
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
