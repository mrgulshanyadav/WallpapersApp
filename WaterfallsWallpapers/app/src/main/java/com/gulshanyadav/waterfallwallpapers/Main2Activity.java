package com.gulshanyadav.waterfallwallpapers;

import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    private ImagePagerAdapter adapter;
    private ViewPager viewPager;
    private String id;

    private boolean clicked,hidden;

    private int[] mImages = new int[]{
            R.drawable.img0, R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4
    };

    AdView adView;
    private InterstitialAd interstitial;

    private Button setwallpaperbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().hide();
        hidden = true;

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle("Wallpapers");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff000000));

        MobileAds.initialize(this,getString(R.string.admob_app_id));  // App Id goes inside initialize
        adView = findViewById(R.id.activity_main2_adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        AdRequest adRequest1 = new AdRequest.Builder().build();
        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(Main2Activity.this);
        // Insert the Ad Unit ID
        interstitial.setAdUnitId(getString(R.string.admob_interstitial_id));
        interstitial.loadAd(adRequest1);
        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });

        sharedpreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        id = sharedpreferences.getString("Id", null);
        clicked = true;

        setwallpaperbtn = findViewById(R.id.set_wallpaper_btn);
        viewPager = findViewById(R.id.viewPager);
        adapter = new ImagePagerAdapter(Main2Activity.this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(Integer.parseInt(id));

        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hidden == true) {
                    getSupportActionBar().show();
                }
                else{
                    getSupportActionBar().hide();
                }
            }
        });

        setwallpaperbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewPager.getCurrentItem();
                int selectedImage = mImages[position];
                Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), selectedImage);
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    wallpaperManager.setBitmap(largeIcon);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    Toast.makeText(Main2Activity.this,"Applied!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void displayInterstitial() {
        // If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }

    private class ImagePagerAdapter extends PagerAdapter {
        private Context mContext;

        ImagePagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return mImages.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((ImageView) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if(clicked == true)
            {
                position = Integer.parseInt(id);
            }
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(mImages[position]);
            container.addView(imageView, 0);
            clicked = false;
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((ImageView) object);
            clicked = false;
        }
    }

}
