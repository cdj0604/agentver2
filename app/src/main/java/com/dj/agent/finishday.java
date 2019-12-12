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
import android.widget.Toast;

import java.util.Calendar;

public class finishday extends Activity {
    TextView textDday;
    Button nextbtn1;
    Button btnDate;


    int dDay = 0;
    int dMonth = 0;
    int dYear = 0;


    Calendar calendar2;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finishday);

        textDday = (TextView) findViewById(R.id.textDday);
        nextbtn1 = (Button) findViewById(R.id.nextbtn1);
        btnDate = (Button) findViewById(R.id.btnDate);

        /*소집해제 선택 날짜구하기  */
        calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MILLISECOND, 0);
        dYear = calendar2.get(Calendar.YEAR);
        dMonth = calendar2.get(Calendar.MONTH);
        dDay = calendar2.get(Calendar.DAY_OF_MONTH);

        btnDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(finishday.this, mDateSetListener, dYear, dMonth, dDay).show();
            }
        });
      //  다음버튼
       nextbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), insert.class);
                startActivity(intent);
            }
        });

    }
        DatePickerDialog.OnDateSetListener mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                dYear=year;
                dMonth=monthOfYear;
                dDay=dayOfMonth;
                calendar2.set(Calendar.YEAR, dYear);
                calendar2.set(Calendar.MONTH, dMonth);
                calendar2.set(Calendar.DATE, dDay);
                long finishD = calendar2.getTimeInMillis() / (24 * 60 * 60 * 1000);//소집해제 선택날짜를 상수로 변환
                UpdateDday();
            }

        };

    void UpdateDday() {
        long finish = calendar2.getTimeInMillis() / (24 * 60 * 60 * 1000);
        String finishD = String.valueOf(finish);
        textDday.setText(String.format("%d.%d.%d", dYear, dMonth+1, dDay));  //선택 날짜 출력
        String date = String.format("%d.%d.%d", dYear, dMonth+1, dDay); //선택날짜 스트링값 변환
        if (date.getBytes().length<=0){
            Toast.makeText(getApplicationContext(), "날짜를 정확히 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        SharedPreferences pref = getSharedPreferences("PREFERENCE", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("finishD",finishD); //startD = 입소날 선택날 상수값
        editor.putString("finishday",date);//입소날 출력값 2019.3.29
        int finishLevel = dYear*12-dMonth+1 ;
        editor.putInt("finishLevel",finishLevel);
        editor.commit();
    }
    }
