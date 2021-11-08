package com.nihalramtripathi.typesnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.nio.MappedByteBuffer;

import hotchemi.android.rate.AppRate;

public class MainActivity extends AppCompatActivity {


    InterstitialAd mInterstitialAd = null;
    TabLayout tabLayout;
    TabItem home, sports, technology, health, entertainment, bollywood;
    PagerAdaptor pagerAdaptor;
    Toolbar toolbar;
    String api = "f7ea1686df934d33af95f49ed2cd3964";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*

        MobileAds.initialize(this, new  OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

 */

        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,"ca-app-pub-2939393277152565/7225991627", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded( InterstitialAd interstitialAd) {

                mInterstitialAd = interstitialAd;

            }

            @Override
            public void onAdFailedToLoad( LoadAdError loadAdError) {
                // Handle the error

                super.onAdFailedToLoad(loadAdError);
                //  mInterstitialAd.loadAd(adRequest);
                mInterstitialAd = null;
            }
        });




        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        home = findViewById(R.id.home);
        sports = findViewById(R.id.sports);
        technology = findViewById(R.id.technology);
        health = findViewById(R.id.health);
        entertainment = findViewById(R.id.entertainment);
        bollywood = findViewById(R.id.bollywood);

        ViewPager viewPager = findViewById(R.id.fragment_container);
        tabLayout = findViewById(R.id.include);


        PagerAdaptor pagerAdaptor = new PagerAdaptor(getSupportFragmentManager(), 6);
        viewPager.setAdapter(pagerAdaptor);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2 || tab.getPosition() == 3 || tab.getPosition() == 4 || tab.getPosition() == 5) {

                    pagerAdaptor.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));



        // rate us code for google play store

        AppRate.with(this)
                .setInstallDays(0)
                .setLaunchTimes(3)
                .setRemindInterval(2)
                .monitor();

        AppRate.showRateDialogIfMeetsConditions(this);
    }

    public void onBackPressed() {

        if (mInterstitialAd != null) {
            mInterstitialAd.show(MainActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
        finish();


    }
}


