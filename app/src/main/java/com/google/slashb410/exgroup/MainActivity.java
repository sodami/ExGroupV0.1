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
//        if(serverUrl!=null&&pemKeyUrl!=null)
 NetSSL.getInstance().launch(getApplicationContext());


        goNextActivity();
    }


    public void goNextActivity(){
        Intent intent = new Intent(this, IntroActivity.class);
        startActivity(intent);
        finish();
    }

}
