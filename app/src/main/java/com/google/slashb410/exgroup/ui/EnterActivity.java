package com.google.slashb410.exgroup.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.ui.join.Input1Activity;
import com.google.slashb410.exgroup.util.U;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        ButterKnife.bind(this);



    }

    @OnClick(R.id.joinBtn)
    public void onJoin(){
        U.getInstance().goNext(this, Input1Activity.class, false);
        finish();
    }

    @OnClick(R.id.loginBtn)
    public void onLogin(){
        //최초 로그인 시 튜토리얼 진행
        if(isFirstLogin()){

        }else {
            //아니면 홈화면
            U.getInstance().goNext(this, Home2Activity.class, false);
        }

    }

    public boolean isFirstLogin(){
        return false;
    }
}
