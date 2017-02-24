package com.google.slashb410.exgroup.ui.group.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.group.GroupData;
import com.google.slashb410.exgroup.model.group.group.ResUncertifiedMem;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.ui.group.room.GroupHomeActivity;
import com.google.slashb410.exgroup.util.U;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupWaitingActivity extends AppCompatActivity {

    GroupData groupData;

    @BindView(R.id.title_waiting)
    TextView title;
    @BindView(R.id.nowNum)
    TextView nowNum;
    @BindView(R.id.maxNum)
    TextView maxNum;
    @BindView(R.id.waiting_groupId)
    TextView groupId;
    @BindView(R.id.createDay_waiting)
    TextView createDay;

    String groupId_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_waiting);
        ButterKnife.bind(this);

        //그룹정보 텍스트뷰 세팅
        setGroupInfo();

        //미인증자 세팅
        setUncertifiedMem();


    }

    private void setGroupInfo() {
        groupData = new GroupData();
        Intent intent = getIntent();
        groupData.setGroup_id(intent.getIntExtra("groupId", 0));
        groupData.setGroupTitle(intent.getStringExtra("title"));
        groupData.setNowNum(intent.getIntExtra("nowNum", 0));
        groupData.setMaxNum(intent.getIntExtra("maxNum", 0));
        groupData.setGroupCreateDate(intent.getStringExtra("date"));


        title.setText(groupData.getGroupTitle());
        nowNum.setText(groupData.getNowNum()+"");
        maxNum.setText(groupData.getMaxNum()+"");
        groupId.setText("그룹 ID : "+groupData.getGroup_id());
        createDay.setText(groupData.getGroupCreateDate().substring(0,10));

        groupId_str = String.valueOf(groupData.getGroup_id());

    }

    private void setUncertifiedMem() {

        Call<ResUncertifiedMem> resJoinCall = NetSSL.getInstance().getGroupImpFactory().uncertifiedMem(groupId_str);
        resJoinCall.enqueue(new Callback<ResUncertifiedMem>() {
            @Override
            public void onResponse(Call<ResUncertifiedMem> call, Response<ResUncertifiedMem> response) {
                if (response.body() != null) {
                    U.getInstance().myLog(response.body().toString());


                }else{
                    U.getInstance().myLog("ResUncertifiedMem Body is NULL");
                }
            }

            @Override
            public void onFailure(Call<ResUncertifiedMem> call, Throwable t) {
                U.getInstance().myLog("접근실패 : "+t.toString());
            }
        });

    }

    public void goNext(){
        U.getInstance().goNext(this, GroupHomeActivity.class, false, false);
        finish();
    }

}
