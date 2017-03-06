package com.google.slashb410.exgroup.net;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.StorageHelper;
import com.google.slashb410.exgroup.model.group.ReqSendFCM;
import com.google.slashb410.exgroup.model.group.ResSendFCM;

import retrofit2.Call;
import retrofit2.Callback;

public class RetroTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retro_test);Log.i("BADGE_pkg", getComponentName().getPackageName());

        // 뱃징
        //뱃지 초기화 (앱을 구동하면 무조건 뱃지를 0으로 만든다.
        //만약 특정 메세지를 확인 후에 없애는 방식이면 그 지점으로 이동 시킨다.,
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", 0);
        intent.putExtra("badge_count_package_name",getPackageName());
        intent.putExtra("badge_count_class_name",getComponentName().getClassName());
        sendBroadcast(intent);
        //저장값 초기화
        StorageHelper.getInstance().setInt(this,"APP_NO_READ_MSG",0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //통신
                SendFCM();
            }
        });
    }

    public void SendFCM()
    {
        //통신 준비
        Call<ResSendFCM> resSendFCMCall =
                RetroFitImpFactory.getInstance().getService().sendFCM(new ReqSendFCM("qccMxSm8qfaKPCW2qW4CDm6Aaoq1","hello"));
        //싱글톤 객체에서 끌고 온 것.

        //수행
        resSendFCMCall.enqueue(new Callback<ResSendFCM>() {
            @Override
            public void onResponse(Call<ResSendFCM> call, retrofit2.Response<ResSendFCM> response) {
                Log.i("RETRO성공", response.body().getBody() + " : " + response.body().getCode());
            }

            @Override
            public void onFailure(Call<ResSendFCM> call, Throwable t) {
                //통신 실패
            }
        });

    }


}
