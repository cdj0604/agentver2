package com.dj.agent;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

/**
 * Created by student on 2018-01-11.
 */

public class Menu_4 extends Fragment {

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup view  = (ViewGroup)layoutInflater.inflate(R.layout.menu_4,container,false);
        Button reset = (Button)view.findViewById(R.id.reset);
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("초기화")        // 제목 설정
                        .setMessage("설정을 초기화 하시겠습니까?")        // 메세지 설정
                        .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                        .setPositiveButton("확인", new DialogInterface.OnClickListener(){

                            // 확인 버튼 클릭시 설정

                            public void onClick(DialogInterface dialog, int whichButton){
                                asd();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener(){
                            // 취소 버튼 클릭시 설정
                            public void onClick(DialogInterface dialog, int whichButton){
                                dialog.cancel();
                            }
                        });
                AlertDialog dialog = builder.create();    // 알림창 객체 생성
                dialog.show();
            }
        });
        return view;
    }

  /*  public void reset(View v){
       // 알림창 띄우기
    }*/

    public void asd(){
        Intent intent = new Intent(getActivity(),startday.class);
        startActivity(intent);
        getActivity().finish();
    }
}
