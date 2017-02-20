package com.google.slashb410.exgroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.ui.IntroActivity;


public class MainActivity extends AppCompatActivity {

    // 추가
    // 추가2
    // 추가3 test ..
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //facebook 초기화
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        //보안통신 초기화
        NetSSL.getInstance().launch(getApplicationContext());
//
//        //======================================FAKE DATA
//
//        GroupInfo sodams = new GroupInfo("소담이네", "/groupimg01.jpg", 30);
//        GroupInfo heawons = new GroupInfo("혜원이네", "/groupimg02.jpg", 6);
//        GroupInfo seungoks = new GroupInfo("승옥이네", "/groupimg03.jpg", 2);
//        GroupInfo yeonjeongs = new GroupInfo("연정이네", "/groupimg04.jpg", 27);
//
//        E.KEY.group_list.add(sodams);
//        E.KEY.group_list.add(heawons);
//        E.KEY.group_list.add(seungoks);
//        E.KEY.group_list.add(yeonjeongs);
//
//
//
//        //================================================

        goNextActivity();
    }


    public void goNextActivity(){
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
        finish();
    }

}
