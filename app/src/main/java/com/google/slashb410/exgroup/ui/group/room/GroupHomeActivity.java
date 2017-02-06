package com.google.slashb410.exgroup.ui.group.room;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.slashb410.exgroup.R;

public class GroupHomeActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_home);

    //    getSupportActionBar().hide();
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FadingActionBarHelper fadingActionBarHelper = new FadingActionBarHelper()
//                .actionBarBackground(new ColorDrawable(Color.parseColor("#337733")))
//                .headerLayout(R.layout.header_group_home)
//                .contentLayout(R.layout.fragment_group_shots);
//        setContentView(fadingActionBarHelper.createView(this));
//        fadingActionBarHelper.initActionBar(this);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("행성"));
        tabLayout.addTab(tabLayout.newTab().setText("그래프"));
        tabLayout.addTab(tabLayout.newTab().setText("인증샷"));
        tabLayout.addTab(tabLayout.newTab().setText("캘린더"));
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
}
