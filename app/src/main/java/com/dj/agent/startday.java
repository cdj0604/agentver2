package com.dj.agent;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class startday extends Activity {
    TextView textDday1;
    Button nextbtn;
    Button btnDate1;

    int ddDay = 0;
    int ddMonth =0;
    int ddYear = 0;



      Calendar calendar3;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startday);

        textDday1 = (TextView) findViewById(R.id.textDday1);
        nextbtn = (Button) findViewById(R.id.nextbtn);
        btnDate1 = (Button) findViewById(R.id.btnDate1);

        /*입소날 선택 날짜구하기  */
        calendar3 = Calendar.getInstance();
        calendar3.set(Calendar.HOUR_OF_DAY, 0);
        calendar3.set(Calendar.MINUTE, 0);
        calendar3.set(Calendar.SECOND, 0);
        calendar3.set(Calendar.MILLISECOND, 0);
        ddYear = calendar3.get(Calendar.YEAR);
        ddMonth = calendar3.get(Calendar.MONTH);
        ddDay = calendar3.get(Calendar.DAY_OF_MONTH);

        /* 입소날 선택 날짜 구하기 */
        btnDate1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(startday.this, mDateSetListener1, ddYear, ddMonth, ddDay).show();
            }
        });
        /*다음버튼*/
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), finishday.class);
                startActivity(intent);
            }
        });
    }
        DatePickerDialog.OnDateSetListener mDateSetListener1=new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                ddYear=year;
                ddMonth=monthOfYear;
                ddDay=dayOfMonth;
                calendar3.set(Calendar.YEAR, ddYear);
                calendar3.set(Calendar.MONTH, ddMonth);
                calendar3.set(Calendar.DATE, ddDay);
                UpdateDday();
            }
        };
    void UpdateDday() {
        long start = calendar3.getTimeInMillis() / (24 * 60 * 60 * 1000);
        String stadrD = String.valueOf(start);
        textDday1.setText(String.format("%d.%d.%d", ddYear, ddMonth+1, ddDay));  //선택 날짜 출력
        String date = String.format("%d.%d.%d", ddYear, ddMonth+1, ddDay); //선택날짜 스트링값 변환
        SharedPreferences pref = getSharedPreferences("PREFERENCE", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("startD",stadrD); //startD = 입소날 선택날 상수값
        editor.putString("startday",date);//입소날 출력값 2019.3.29
        editor.commit();
    }
    }
