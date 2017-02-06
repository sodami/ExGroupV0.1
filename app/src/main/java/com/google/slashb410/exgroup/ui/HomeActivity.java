package com.google.slashb410.exgroup.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.ui.group.create.GroupAddActivity;
import com.google.slashb410.exgroup.ui.mypage.MyHomeActivity;
import com.google.slashb410.exgroup.util.U;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        final String[] groupName = {"슬비네그룹", "소담이네그룹", "혜원이네", "승옥이네", "추가"};


        GridAdapter gridAdapter = new GridAdapter(this, R.layout.group_card_view, groupName);
        GridView gridView = (GridView) findViewById(R.id.group_grid);
        gridView.setAdapter(gridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position==groupName.length-1){
                    U.getInstance().goNext(getApplicationContext(), GroupAddActivity.class, false);
                }
            }
        });


    }


    @OnClick(R.id.profile_box)
    public void goMyPage(){
        U.getInstance().goNext(this, MyHomeActivity.class, false);
    }



}

