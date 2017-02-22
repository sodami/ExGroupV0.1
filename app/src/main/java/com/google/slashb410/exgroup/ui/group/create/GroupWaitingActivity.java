package com.google.slashb410.exgroup.ui.group.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.group.GroupData;
import com.google.slashb410.exgroup.ui.group.room.GroupHomeActivity;
import com.google.slashb410.exgroup.util.U;

public class GroupWaitingActivity extends AppCompatActivity {

    GroupData groupData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_waiting);

        Intent intent = getIntent();
        groupData.setGroup_id(Integer.parseInt(intent.getStringExtra("groupId")));
        groupData.setGroupTitle(intent.getStringExtra("title"));
        groupData.setNowNum(Integer.parseInt(intent.getStringExtra("nowNum")));
        groupData.setMaxNum(Integer.parseInt(intent.getStringExtra("maxNum")));

        getActionBar().setTitle(groupData.getGroupTitle()+"(활동대기중)");

    }

    public void goNext(){
        U.getInstance().goNext(this, GroupHomeActivity.class, false, false);
        finish();
    }

}
