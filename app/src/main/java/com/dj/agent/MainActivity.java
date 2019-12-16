package com.dj.agent;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment;
    private AdView mAdView;
    private BackPressCloseHandler backPressCloseHandler;
    private int Year , Month;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView menu1_1 = (ImageView)findViewById(R.id.imageView);
        TextView printstart = (TextView)findViewById(R.id.start) ;
        TextView bm = (TextView)findViewById(R.id.bm); //소집해제날짜 출력
        TextView ddaytext = (TextView)findViewById(R.id.textview1);
        TextView textview6 = (TextView)findViewById(R.id.textview6);
        TextView textview = (TextView)findViewById(R.id.textview);
        TextView textViewp = (TextView)findViewById(R.id.TextViewp);
        TextView testtext = (TextView)findViewById(R.id.testtext);


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

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        String formatDate = simpleDateFormat.format(date);
        int fdate = Integer.parseInt(formatDate);
        Log.d("현재시간", String.valueOf(fdate));
        if (fdate > 0600 && fdate <1059){
            menu1_1.setImageResource(R.drawable.menu1_1);
        }
        else if (fdate > 1100 && fdate <1759){
            menu1_1.setImageResource(R.drawable.menu1_3);
        }
        else menu1_1.setImageResource(R.drawable.menu1_2);
        /*String userid = getArguments().getString("userid");
        TextView tv = (TextView) view.findViewById(R.id.firstmainText);
        tv.setText(userid);*/
        SharedPreferences pref = getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
        String start = pref.getString("startD", String.valueOf(0));
        SharedPreferences.Editor editor = pref.edit();
        String Stringstart = pref.getString("startday",String.valueOf(0));
        printstart.setText(Stringstart);//소집일날 출력
        int startLevel = pref.getInt("startLevel",0);//입소날 년,월 가져오기
        int todayLevel = Year*12+Month-1; //오늘 년.월가져오기
        int a = todayLevel-startLevel;
        int d = 0;
        String c = null;
        Log.d("계급/달수차이", String.valueOf(a));
        if (a<3) {
            testtext.setText("이병");
            d = 306100;
            c = "306100";
        }
        else if (a < 10){
            testtext.setText("일병");
            d = 331300;
            c = "331300";

        }
        else if (a < 18){
            testtext.setText("상병");
            d = 366200;
            c = "366200";

        }
        else if(a>=18){
            testtext.setText("병장");
            d = 405700;
            c = "405700";

        }
        editor.putInt("money", d);
        editor.putString("Level",c);
        editor.commit();
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
            ProgressBar progress = (ProgressBar)findViewById(R.id.progress) ;
            progress.setProgress(value) ;

        } catch (Exception e) {

        }


        //------------------------애드몹---------------------
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        //---------------------------------------------------
        backPressCloseHandler = new BackPressCloseHandler(this);

        //광고 제대로 나오는지 테스트하기 위한 코드
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                // 광고가 문제 없이 로드시 출력됩니다.
                Log.d("@@@", "onAdLoaded");
            }
            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                // 광고 로드에 문제가 있을시 출력됩니다.
                Log.d("@@@", "onAdFailedToLoad " + errorCode);
            }

            @Override

            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }
            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

        });
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

      /*  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            backPressCloseHandler.onBackPressed();
          //  super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_1) {
                fragment = new Menu_1();

        } else if (id == R.id.menu_2) {
                fragment = new Menu_2();
        } else if (id == R.id.menu_3) {
            fragment = new Menu_3();
        } else if (id == R.id.menu_4) {
           reset();
        } else if (id == R.id.menu_5) {
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.dj.agent"));
            startActivity(myIntent);
        } else if (id == R.id.menu_6) {
            send();
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.StartPage, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void asd(){
        Intent intent = new Intent(this,startday.class);
        startActivity(intent);
        this.finish();
    }
    public void send(){
        Intent email = new Intent(Intent.ACTION_SEND);
        email.setType("plain/text");
        // email setting 배열로 해놔서 복수 발송 가능
        String[] address = {"cdj0604@icloud.com"};
        email.putExtra(Intent.EXTRA_EMAIL, address);
        email.putExtra(Intent.EXTRA_SUBJECT,"[공붕이] 문의사항입니다 ");
        //  email.putExtra(Intent.EXTRA_TEXT,"보낼 email 내용을 미리 적어 놓을 수 있습니다.\n");
        startActivity(email);
    }
    public void reset(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
}
