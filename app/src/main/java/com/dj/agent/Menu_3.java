package com.dj.agent;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

import androidx.fragment.app.Fragment;


public class Menu_3 extends Fragment {

    private InterstitialAd mInterstitialAd;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup view  = (ViewGroup)layoutInflater.inflate(R.layout.menu_3,container,false);
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //구글애드몹
        MobileAds.initialize(getActivity(), "@string/admob_app_id");
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("@string/j_ad_unit_id_for_test");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {

            }


        });
       Button infor1 = view.findViewById(R.id.infor1);
        Button infor2 =view.findViewById(R.id.infor2);
        Button infor3  =view.findViewById(R.id.infor3);
        Button infor4  =view.findViewById(R.id.infor4);

        infor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), com.dj.agent.infor1.class);
                startActivity(i);
                //애드몹
                if (mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                }
                else{
                    Log.d("전면광고","광고로드실패");
                }

            }
        });

        return view;
    }
}
