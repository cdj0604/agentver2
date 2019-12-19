package com.dj.agent;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.fragment.app.Fragment;


public class Menu_2 extends Fragment {

    int d;
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup view  = (ViewGroup)layoutInflater.inflate(R.layout.menu_2,container,false);

        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView5);
        Button sum = (Button) view.findViewById(R.id.nextbtn);
        TextView testeat = (TextView)view.findViewById(R.id.eat);
        TextView testbus = (TextView)view.findViewById(R.id.bus);
        TextView testlevel = (TextView)view.findViewById(R.id.level);
        final EditText input1 = (EditText) view.findViewById(R.id.edit01);//출근일수입력
        final TextView result = (TextView) view.findViewById(R.id.textView3);//월급보여주기

        final SharedPreferences pref = getActivity().getSharedPreferences("PREFERENCE", Activity.MODE_PRIVATE);
        final String eat = pref.getString("insert_eat", "");
        final String bus = pref.getString("insert_bus", "");
        String level= pref.getString("Level","");
        testeat.setText(eat+"원");
        testbus.setText(bus+"원");
        testlevel.setText(level+"원");

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        String formatDate = simpleDateFormat.format(date);
        int fdate = Integer.parseInt(formatDate);
        Log.d("현재시간", String.valueOf(fdate));
        if (fdate > 0600 && fdate <1059){
            imageView.setImageResource(R.drawable.menu2_1);
            Picasso.with(getActivity()).load(R.drawable.menu2_1).into(imageView);

        }
        else if (fdate > 1100 && fdate <1759){
            Picasso.with(getActivity()).load(R.drawable.menu2_2).into(imageView);

        }
        else
            Picasso.with(getActivity()).load(R.drawable.menu2_3).into(imageView);



        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int d = pref.getInt("money",0);
                String getinput1 = input1.getText().toString();//출근일수입력
                input1.setSelection(input1.length()); //뒤부터입력
                if (getinput1.getBytes().length <= 0) {
                    Toast.makeText(getActivity().getApplicationContext(), "계급과 출근일수를 정확히 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    int intbus = Integer.parseInt(bus);
                    int inteat = Integer.parseInt(eat);
                    int a = Integer.parseInt(input1.getText().toString()); //출근일수
                    int sum = (a * intbus) + (a * inteat) + d;

                    String stringsum = Integer.toString(sum);

                    result.setVisibility(View.VISIBLE); //버튼클릭시 월급보여주기
                    result.setText(stringsum + " 원");


                }

            }
        });
        return view;


    }
}
