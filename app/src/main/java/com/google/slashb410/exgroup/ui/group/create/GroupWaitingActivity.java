package com.google.slashb410.exgroup.ui.group.create;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.model.group.group.GroupData;
import com.google.slashb410.exgroup.model.group.group.ResUncertifiedMem;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.ui.group.room.GroupHomeActivity;
import com.google.slashb410.exgroup.util.U;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.back_waiting)
    ImageButton backwaiting;
    @BindView(R.id.cancel_waiting)
    ImageButton canclewaiting;

    String groupId_str;

    @BindView(R.id.pushBtn)
    Button pushBtn;
    @BindView(R.id.group_id_box_waiting)
    LinearLayout groupIdBox;
    @BindView(R.id.reinviteBtn)
    Button reInviteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_waiting);
        ButterKnife.bind(this);

        //1. 그룹정보 세팅, groupId_str 세팅
        setGroupInfo();

        //2. 그룹상태 불러오기
        int groupState = chkGroupState();

        //3. 상태에 맞는 뷰 세팅
        setViewByState(groupState);
    }
//    @OnClick(R.id.back_waiting)
//    public void onBack() {
//        onBack();
//    }

    @OnClick(R.id.cancel_waiting)
    public void onCancel() {
        finish();
    }



    private void setViewByState(int groupState) {


        switch (groupState){
            case 0: //2-1. 본인 인증 전
                setNoMyWeight();
                break;
            case 1: //2-2. 가입 대기, 가입자 전원 인증 > 불참자 초대 버튼
                setReInvite();
                break;
            case 2: //2-3. 가입 대기, 가입자 일부 인증 > 불참자 초대 버튼, 미인증자 세팅
                setReInvite();
                setUncertifiedMem();
                break;
            case 3: //2-4. 전원 가입, 가입자 일부 인증 > 미인증자 세팅
                setUncertifiedMem();
                break;

        }


    }

    private void setNoMyWeight() {

    }

    private void setReInvite() {
        groupIdBox.setVisibility(View.VISIBLE);
        reInviteBtn.setVisibility(View.VISIBLE);
    }

    private int chkGroupState() {

        final int[] groupState = {0};

        Call<ResStandard> resGroupState = NetSSL.getInstance().getGroupImpFactory().groupState(groupId_str);
        resGroupState.enqueue(new Callback<ResStandard>() {
            @Override
            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                if (response.body() != null) {
                    groupState[0] = response.body().getResultCode();

                }else{
                    U.getInstance().myLog("ResGroupState Body is NULL");
                }
            }

            @Override
            public void onFailure(Call<ResStandard> call, Throwable t) {
                U.getInstance().myLog("접근실패 : "+t.toString());
            }
        });

        return groupState[0];

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


        //+미인증자 재촉하기 버튼세팅
        pushBtn.setVisibility(View.VISIBLE);
    }

    public void goNext(){
        U.getInstance().goNext(this, GroupHomeActivity.class, false, false);
        finish();
    }


}
