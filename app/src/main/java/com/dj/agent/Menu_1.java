package com.dj.agent;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

import androidx.fragment.app.Fragment;

/**
 * Created by student on 2018-01-11.
 */

public class Menu_1 extends Fragment {


    private int Year;
    private int Month;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup view  = (ViewGroup)layoutInflater.inflate(R.layout.content_main,container,false);
        TextView printstart = (TextView)view.findViewById(R.id.start) ;
        TextView bm = (TextView)view.findViewById(R.id.bm); //소집해제날짜 출력
        TextView ddaytext = (TextView)view.findViewById(R.id.textview1);
        TextView textview6 = (TextView)view.findViewById(R.id.textview6);
        TextView textview = (TextView)view.findViewById(R.id.textview);
        TextView textViewp = (TextView)view.findViewById(R.id.TextViewp);
        TextView testtext = (TextView)view.findViewById(R.id.testtext);

        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /* 오늘 날짜 구하기 */
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        long ttoday=calendar.getTimeInMillis()/(24*60*60*1000);
        int r1 = (int)(long) ttoday;// 현재날짜 int로 변환


        /*String userid = getArguments().getString("userid");
        TextView tv = (TextView) view.findViewById(R.id.firstmainText);
        tv.setText(userid);*/
        SharedPreferences pref = getActivity().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
        String start = pref.getString("startD", String.valueOf(0));
        String Stringstart = pref.getString("startday",String.valueOf(0));
        printstart.setText(Stringstart);//소집일날 출력

        int startLevel = pref.getInt("startLevel",0);//입소날 년,월 가져오기
        int todayLevel = Year*12+Month-1; //오늘 년.월가져오기
        int a = todayLevel-startLevel;
        int d = 0;
        Log.d("계급/달수차이", String.valueOf(a));
        if (a<3) {
            testtext.setText("이등병");
            d = 306100;
        }
        else if (a < 10){
            testtext.setText("일등병");
            d = 331300;
        }
        else if (a < 18){
            testtext.setText("상등병");
            d = 366200;
        }
        else {
            testtext.setText("병장");
            d = 405700;
        }
        int startday = Integer.parseInt(start); //소집날 int값

        String finish = pref.getString("finishD", String.valueOf(0));
        int finishday = Integer.parseInt(finish);//소집해제날 int값
        int dday = finishday-r1;
        String Stringdday = Integer.toString(dday);
        ddaytext.setText(Stringdday);

        String printfinish = pref.getString("finishday", String.valueOf(0)); //출력될소집해제 날짜값 2021.2.22
        bm.setText(printfinish);

        int allday = finishday-startday;
        String getallday = Integer.toString(allday);
        textview6.setText(getallday); //총복무일출력

        int today = r1-startday;
        String gettoday = Integer.toString(today);
        textview.setText(gettoday);//현재복무일수 출력

        int percent = (int) ((double) today / (double) allday * 100.0);
        String pp = Integer.toString(percent);
        textViewp.setText(pp+"/100(%)" );


        //---------------------프로그래스 바------------------------------
        try {
            // 문자열을 숫자로 변환.
            int value = Integer.parseInt(pp);
            // 변환된 값을 프로그레스바에 적용.
            ProgressBar progress = (ProgressBar) view.findViewById(R.id.progress) ;
            progress.setProgress(value) ;

        } catch (Exception e) {

        }

        return view;


    }



}
