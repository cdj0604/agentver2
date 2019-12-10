package com.dj.agent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.gms.ads.MobileAds;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(this, getString(R.string.admob_app_id));
        SharedPreferences pref = getSharedPreferences("PREFERENCE", Activity.MODE_PRIVATE);
        String a =  pref.getString("key01", String.valueOf(0));

        File file = new File("/data/data/com.dj.agent/shared_prefs/PREFERENCE.xml");
        if (file.exists()){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            Intent intent1 = new Intent(this,startday.class);
            startActivity(intent1);
            finish();
        }

    }
}