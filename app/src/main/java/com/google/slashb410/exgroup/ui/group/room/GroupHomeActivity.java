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

        //랭크 세팅
        setRankList();

        //그룹아이디 세팅 for Fragment
        groupId = groupData.getGroup_id();


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.planet_white));
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

    @BindView(R.id.rank1)
    LinearLayout rank1;
    @BindView(R.id.rank2)
    LinearLayout rank2;
    @BindView(R.id.rank3)
    LinearLayout rank3;
    @BindView(R.id.rank4)
    LinearLayout rank4;
    @BindView(R.id.rank5)
    LinearLayout rank5;

    @BindView(R.id.rank1_nick)
    TextView nick1;
    @BindView(R.id.rank2_nick)
    TextView nick2;
    @BindView(R.id.rank3_nick)
    TextView nick3;
    @BindView(R.id.rank4_nick)
    TextView nick4;
    @BindView(R.id.rank5_nick)
    TextView nick5;

    @BindView(R.id.rank1_profile)
    ImageView profile1;
    @BindView(R.id.rank2_profile)
    ImageView profile2;
    @BindView(R.id.rank3_profile)
    ImageView profile3;
    @BindView(R.id.rank4_profile)
    ImageView profile4;
    @BindView(R.id.rank5_profile)
    ImageView profile5;

    ArrayList<RankUser> rankUsers;

    private void setRankList() {
        rankUsers = new ArrayList<>();

        //1.랭킹 정보 요청
        Call<ResRank> resRank = NetSSL.getInstance().getGroupImpFactory().rank(groupData.getGroup_id());
        resRank.enqueue(new Callback<ResRank>() {
            @Override
            public void onResponse(Call<ResRank> call, Response<ResRank> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getResultCode() == 1) {
                            //2. 랭킹 정보 박기
                            rankUsers = response.body().getResult().getData();

                            nick1.setText(rankUsers.get(0).getNickname());
                            nick2.setText(rankUsers.get(1).getNickname());

                            Picasso.with(GroupHomeActivity.this).load(rankUsers.get(0).getPicUrl()).fit().centerCrop().into(profile1);
                            Picasso.with(GroupHomeActivity.this).load(rankUsers.get(1).getPicUrl()).fit().centerCrop().into(profile2);


                            //3. 사람수만큼 visibility 세팅
                            switch (rankUsers.size()) {
                                case 0:
                                    U.getInstance().myLog("groupData.getMaxNum == 0");
                                    break;
                                case 2:

                                    rank3.setVisibility(View.GONE);
                                    rank4.setVisibility(View.GONE);
                                    rank5.setVisibility(View.GONE);

                                    break;
                                case 3:
                                    nick3.setText(rankUsers.get(2).getNickname());
                                    Picasso.with(GroupHomeActivity.this).load(rankUsers.get(2).getPicUrl()).fit().centerCrop().into(profile3);

                                    rank4.setVisibility(View.GONE);
                                    rank5.setVisibility(View.GONE);

                                    break;
                                case 4:
                                    nick3.setText(rankUsers.get(2).getNickname());
                                    nick4.setText(rankUsers.get(3).getNickname());
                                    Picasso.with(GroupHomeActivity.this).load(rankUsers.get(2).getPicUrl()).fit().centerCrop().into(profile3);
                                    Picasso.with(GroupHomeActivity.this).load(rankUsers.get(3).getPicUrl()).fit().centerCrop().into(profile4);

                                    rank5.setVisibility(View.GONE);

                                    break;
                                case 5:
                                    nick3.setText(rankUsers.get(2).getNickname());
                                    nick4.setText(rankUsers.get(3).getNickname());
                                    nick5.setText(rankUsers.get(4).getNickname());
                                    Picasso.with(GroupHomeActivity.this).load(rankUsers.get(2).getPicUrl()).fit().centerCrop().into(profile3);
                                    Picasso.with(GroupHomeActivity.this).load(rankUsers.get(3).getPicUrl()).fit().centerCrop().into(profile4);
                                    Picasso.with(GroupHomeActivity.this).load(rankUsers.get(4).getPicUrl()).fit().centerCrop().into(profile5);

                                    break;

                            }

                        }
                    } else {
                        U.getInstance().myLog("resRank Body is null :" + response.message());
                    }
                } else {
                    U.getInstance().myLog("resRank is not successful : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<ResRank> call, Throwable t) {

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
