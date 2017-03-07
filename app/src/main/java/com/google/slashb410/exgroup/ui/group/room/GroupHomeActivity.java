package com.google.slashb410.exgroup.ui.group.room;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.group.GroupData;
import com.google.slashb410.exgroup.ui.write.QuickWriteActivity;

public class GroupHomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    TextView groupTitleView;
    ImageView groupImg;

    public GroupData groupData;
    public int groupId;
    Uri picUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.collapse_toolbar);
        setSupportActionBar(toolbar);

        //기본정보 저장
        groupData = new GroupData();
        Intent intent = getIntent();
        groupData = (GroupData) intent.getSerializableExtra("groupData");

        //그룹아이디 세팅 for Fragment
        groupId = groupData.getGroup_id();

        CollapsingToolbarLayout layout = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar_layout);
      //  groupTitleView = (TextView) findViewById(R.id.group_home_title);
        groupImg = (ImageView) findViewById(R.id.collapseImg);

        if(groupData.getGroupPicUrl()!=null) {
            picUrl = Uri.parse(groupData.getGroupPicUrl());
        }

        if (groupData.getGroupTitle()!=null) {
//            groupTitleView.setText(groupData.getGroupTitle());
            groupImg.setImageURI(picUrl);
        }


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.planet_white));
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
                            switch (which){
                                case 0 : intent.putExtra("menu", 1);
                                    break;
                                case 1 : intent.putExtra("menu", 2);
                                    break;
                                case 2 : intent.putExtra("menu", 3);
                                    break;
                            }
                            startActivity(intent);
                        }
                    }).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
