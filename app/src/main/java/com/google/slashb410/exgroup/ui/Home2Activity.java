package com.google.slashb410.exgroup.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.slashb410.exgroup.GridAdapter;
import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.ui.mypage.MyHomeActivity;
import com.google.slashb410.exgroup.ui.write.WriteExcerciseActivity;
import com.google.slashb410.exgroup.ui.write.WriteMealActivity;
import com.google.slashb410.exgroup.ui.write.WriteWeightActivity;
import com.google.slashb410.exgroup.util.U;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Home2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.fab)
    FloatingActionMenu floatingActionMenu;
    @BindView(R.id.quick_scale)
    FloatingActionButton scaleQuick;
    @BindView(R.id.quick_exercise)
    FloatingActionButton exerciseQuick;
    @BindView(R.id.quick_meal)
    FloatingActionButton mealQuick;

    GridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2_acrivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String token = FirebaseInstanceId.getInstance().getToken();
//        Log.i("토큰 확인 : ", token);

        //------------------------------------------------------------------------------------------
        // 2017. 02. 01 추가
        ButterKnife.bind(this);


        // final String[] groupName = {/*"슬비네그룹",*/ "소담이네그룹", "혜원이네", "승옥이네" , "연정이네"};

        gridAdapter = new GridAdapter(this, R.layout.group_card_view, E.KEY.group_list);
        GridView gridView = (GridView) findViewById(R.id.group_grid);
        gridAdapter.notifyDataSetChanged();
        gridView.setAdapter(gridAdapter);

//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                if(position==groupName.length-1){
//                    U.getInstance().goNext(getApplicationContext(), GroupAddActivity.class, false, false);
//                }else{
//                     U.getInstance().goNext(getApplicationContext(), GroupHomeActivity.class, false, false);
//                }
//            }
//        });
        //-------------------------------------------------------------------------------------------

        scaleQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                U.getInstance().goNext(getApplicationContext(), WriteWeightActivity.class, false, false);
            }
        });
        exerciseQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                U.getInstance().goNext(getApplicationContext(), WriteExcerciseActivity.class, false, false);
            }
        });

        mealQuick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                U.getInstance().goNext(getApplicationContext(), WriteMealActivity.class, false, false);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        gridAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this, EnterActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {
            Context mContext = getApplicationContext();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.activity_developer_message, (ViewGroup) findViewById(R.id.popup));
            AlertDialog.Builder aDialog = new AlertDialog.Builder(Home2Activity.this);
            aDialog.setView(layout); //dialog.xml 파일을 뷰로 셋팅
            aDialog.setNegativeButton("send", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            //팝업창 만들기
            AlertDialog ad = aDialog.create();
            ad.show();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // 2017. 02. 01 추가
    @OnClick(R.id.profile_box)
    public void goMyPage() {
        U.getInstance().goNext(this, MyHomeActivity.class, false, false);
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            E.KEY.group_list.clear();
            E.KEY.new_group.deleteGroupInfo();
            E.KEY.new_write.deleteWriteData();
            E.KEY.shotData.deleteShotData();
            this.finish();


        }
        return true;
    }
}
