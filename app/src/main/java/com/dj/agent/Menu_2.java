package com.dj.agent;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.fragment.app.Fragment;

/**
 * Created by student on 2018-01-11.
 */

public class Menu_2 extends Fragment {

    int d;
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup view  = (ViewGroup)layoutInflater.inflate(R.layout.menu_2,container,false);

        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Button Button1 = (Button)view.findViewById(R.id.button);
        Button Button2 = (Button)view.findViewById(R.id.button2);
        Button Button3 = (Button)view.findViewById(R.id.button3);
        Button Button4 = (Button)view.findViewById(R.id.button4);
        Button sum = (Button) view.findViewById(R.id.nextbtn);
        final EditText input1 = (EditText) view.findViewById(R.id.edit01);//출근일수입력
        final TextView result = (TextView) view.findViewById(R.id.textView3);//월급보여주기



        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=306130;
            }
        });
        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=306130;
            }
        });

        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=306130;
            }
        });

        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d=306130;
            }
        });

        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("PREFERENCE", Activity.MODE_PRIVATE);
                String eat = pref.getString("insert_eat", "");
                String bus = pref.getString("insert_bus", "");
                String getinput1 = input1.getText().toString();//출근일수입력
                input1.setSelection(input1.length()); //뒤부터입력
                if (getinput1.getBytes().length <= 0 || d==0) {
                    Toast.makeText(getActivity().getApplicationContext(), "계급과 출근일수를 정확히 입력해주세요.", Toast.LENGTH_SHORT).show();

                } else {
                    int intbus = Integer.parseInt(bus);
                    int inteat = Integer.parseInt(eat);
                    int a = Integer.parseInt(input1.getText().toString()); //출근일수
                    int sum = (a * intbus) + (a * inteat) + d;

                    String stringsum = Integer.toString(sum);

                    result.setVisibility(View.VISIBLE); //버튼클릭시 월급보여주기
                    result.setText("  이번달월급 "+stringsum + " 원");


                }

            }
        });
        return view;


    }
}
