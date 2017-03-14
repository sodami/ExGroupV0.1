package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.group.GroupData;
import com.google.slashb410.exgroup.model.group.group.ResGroupCalendar;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupCalListDialogActivity extends AppCompatActivity {

    ListView list;
    CalListAdapter adapter;

    GroupData groupData;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_list_dialog);

        list = (ListView) findViewById(R.id.list_cal_list);

        Intent intent = getIntent();
        groupData = (GroupData) intent.getSerializableExtra("groupData");
        date = intent.getStringExtra("date");

        callCalList();
    }

    private void callCalList() {
        Call<ResGroupCalendar> resGroupCalendar = NetSSL.getInstance().getGroupImpFactory().groupCalendar(groupData.getGroup_id(), date);
        resGroupCalendar.enqueue(new Callback<ResGroupCalendar>() {
            @Override
            public void onResponse(Call<ResGroupCalendar> call, Response<ResGroupCalendar> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null){
                        adapter = new CalListAdapter(GroupCalListDialogActivity.this, response.body().getResult());
                        list.setAdapter(adapter);
                    }else{
                        U.getInstance().myLog("resGroupCalendar Body is null : "+response.message());
                    }
                }else{
                    U.getInstance().myLog("resGroupCalendar is NOT successful : "+response.message());
                }
            }

            @Override
            public void onFailure(Call<ResGroupCalendar> call, Throwable t) {
                U.getInstance().myLog("resGroupCalendar 접근실패 : "+t.toString());
            }
        });
    }
}
