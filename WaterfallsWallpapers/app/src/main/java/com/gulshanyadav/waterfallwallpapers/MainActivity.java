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

        s=new Spacecraft();
        s.setId("5");
        s.setImage(R.drawable.img5);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("6");
        s.setImage(R.drawable.img6);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("7");
        s.setImage(R.drawable.img7);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("8");
        s.setImage(R.drawable.img8);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("9");
        s.setImage(R.drawable.img9);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("10");
        s.setImage(R.drawable.img10);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("11");
        s.setImage(R.drawable.img11);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("12");
        s.setImage(R.drawable.img12);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("13");
        s.setImage(R.drawable.img13);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("14");
        s.setImage(R.drawable.img14);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("15");
        s.setImage(R.drawable.img15);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("16");
        s.setImage(R.drawable.img16);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("17");
        s.setImage(R.drawable.img17);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("18");
        s.setImage(R.drawable.img18);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("19");
        s.setImage(R.drawable.img19);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("20");
        s.setImage(R.drawable.img20);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("21");
        s.setImage(R.drawable.img21);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("22");
        s.setImage(R.drawable.img22);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("23");
        s.setImage(R.drawable.img23);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("24");
        s.setImage(R.drawable.img24);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("25");
        s.setImage(R.drawable.img25);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("26");
        s.setImage(R.drawable.img26);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("27");
        s.setImage(R.drawable.img27);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("28");
        s.setImage(R.drawable.img28);
        spacecrafts.add(s);

        s=new Spacecraft();
        s.setId("29");
        s.setImage(R.drawable.img29);
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
