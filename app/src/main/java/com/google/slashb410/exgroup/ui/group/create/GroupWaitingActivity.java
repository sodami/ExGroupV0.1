package com.google.slashb410.exgroup.ui.group.create;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.ui.group.room.GroupHomeActivity;
import com.google.slashb410.exgroup.util.U;

public class GroupWaitingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_waiting);

        Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0){
                    goNext();
                }
            }
        };
        U.getInstance().onProgress(handler, this, "잠시만 기다려주세요.");

    }


    public void goNext(){
        U.getInstance().goNext(this, GroupHomeActivity.class, false, false);
        finish();
    }

}
