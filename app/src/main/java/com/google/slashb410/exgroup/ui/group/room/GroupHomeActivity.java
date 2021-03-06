package com.google.slashb410.exgroup.ui.group.room;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.group.GroupData;
import com.google.slashb410.exgroup.model.group.group.RankUser;
import com.google.slashb410.exgroup.model.group.group.ResRank;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.ui.write.QuickWriteActivity;
import com.google.slashb410.exgroup.util.U;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupHomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    public GroupData groupData;
    public int groupId;

    @BindView(R.id.group_title_toolbar)
    TextView title;
    @BindView(R.id.collapseImg)
    ImageView groupPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.collapse_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        //기본정보 저장
        groupData = new GroupData();
        Intent intent = getIntent();
        groupData = (GroupData) intent.getSerializableExtra("groupData");

        //툴바 세팅
        title.setText(groupData.getGroupTitle());
        Picasso.with(this).load(groupData.getGroupPicUrl())
                .fit().centerCrop()
                .into(groupPic);


        //그룹아이디 세팅 for Fragment
        groupId = groupData.getGroup_id();


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.trophy_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.chart_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.feed_white));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.calendar_white));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.group_home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.write) {
            String[] items = {"체중", "운동", "식단"};

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("어느 인증샷을 업로드하시겠습니까?")
                    .setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(GroupHomeActivity.this, QuickWriteActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            switch (which) {
                                case 0:
                                    intent.putExtra("menu", 1);
                                    break;
                                case 1:
                                    intent.putExtra("menu", 2);
                                    break;
                                case 2:
                                    intent.putExtra("menu", 3);
                                    break;
                            }
                            startActivity(intent);
                        }
                    }).show();
            return true;
        } else if (id == R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}
