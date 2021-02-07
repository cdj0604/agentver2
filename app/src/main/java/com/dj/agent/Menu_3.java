package com.dj.agent;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


public class Menu_3 extends Fragment {

    private InterstitialAd mInterstitialAd;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup view  = (ViewGroup)layoutInflater.inflate(R.layout.menu_3,container,false);

       AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ImageView imageView2 = (ImageView)view.findViewById(R.id.imageView2);

        MobileAds.initialize(getActivity(), "@string/admob_app_id");
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("@string/j_ad_unit_id");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        String formatDate = simpleDateFormat.format(date);
        int fdate = Integer.parseInt(formatDate);
        Log.d("현재시간", String.valueOf(fdate));
        if (fdate > 0600 && fdate <1059){
         //   imageView2.setImageResource(R.drawable.menu3_1);
            Picasso.with(getActivity()).load(R.drawable.menu3_1).into(imageView2);

        }
        else if (fdate > 1100 && fdate <1759){
          //  imageView2.setImageResource(R.drawable.menu3_3);
            Picasso.with(getActivity()).load(R.drawable.menu3_3).into(imageView2);

        }
        else
            //imageView2.setImageResource(R.drawable.menu3_2);
        Picasso.with(getActivity()).load(R.drawable.menu3_2).into(imageView2);


        mInterstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("asd", "The interstitial wasn't loaded yet.");
                }
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getContext(),"감사합니다",Toast.LENGTH_LONG).show();
            }


        });
        Button infor1 = view.findViewById(R.id.infor1);
        Button infor2 =view.findViewById(R.id.infor2);
        Button infor3  =view.findViewById(R.id.infor3);
        Button infor4  =view.findViewById(R.id.infor4);
        Button infor5  =view.findViewById(R.id.infor5);

        infor5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("월급,진급일 정보")        // 제목 설정
                        .setMessage("2019년도 군인월급 \n 이병 : 459,100 원\n 일병 : 496,900 원\n 상병 : 549,200 원\n 병장 : 608,500 원\n \n2020년도 군인 월급\n 이병 : 408,100 원\n 일병 : 441,600 원\n 상병 : 488,200 원\n 병장 : 540,900 원 \n \n     진급표\n이병 : 소집일~2개월\n일병 : 3개월 ~ 8개월\n상병 : 9개월 ~ 14개월\n병장 : 15개월 ~")        // 메세지 설정
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                        .setNegativeButton("확인", new DialogInterface.OnClickListener(){
                            // 취소 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton){
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();    // 알림창 객체 생성
                dialog.show();
            }
        });

        infor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterstitialAd.setAdListener(new AdListener() {
                    public void onAdLoaded(){
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("asd", "The interstitial wasn't loaded yet.");
                        }
                    }
                });
                Intent i = new Intent(getContext(), com.dj.agent.infor1.class);
                startActivity(i);

            }
        });

        infor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInterstitialAd.setAdListener(new AdListener() {
                    public void onAdLoaded(){
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("asd", "The interstitial wasn't loaded yet.");
                        }
                    }
                });
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.law.go.kr/%ED%96%89%EC%A0%95%EA%B7%9C%EC%B9%99/%EC%82%AC%ED%9A%8C%EB%B3%B5%EB%AC%B4%EC%9A%94%EC%9B%90%EB%B3%B5%EB%AC%B4%EA%B4%80%EB%A6%AC%EA%B7%9C%EC%A0%95"));
                startActivity(myIntent);

            }
        });

        infor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), com.dj.agent.infor3.class);
                startActivity(i);
                mInterstitialAd.setAdListener(new AdListener() {
                    public void onAdLoaded(){
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("asd", "The interstitial wasn't loaded yet.");
                        }
                    }
                });
            }
        });

        infor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), com.dj.agent.infor4.class);
                startActivity(i);
                mInterstitialAd.setAdListener(new AdListener() {
                    public void onAdLoaded(){
                        if (mInterstitialAd.isLoaded()) {
                            mInterstitialAd.show();
                        } else {
                            Log.d("asd", "The interstitial wasn't loaded yet.");
                        }
                    }
                });
            }
        });

        return view;
    }


}
