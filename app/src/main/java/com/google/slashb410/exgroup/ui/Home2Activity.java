package com.google.slashb410.exgroup.ui;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.slashb410.exgroup.GridAdapter;
import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.db.StorageHelper;
import com.google.slashb410.exgroup.model.group.group.ResGroupData;
import com.google.slashb410.exgroup.model.group.group.ResGroupList;
import com.google.slashb410.exgroup.model.group.home.ResMe;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.ui.group.search.GroupSearchActivity;
import com.google.slashb410.exgroup.ui.mypage.MyHomeActivity;
import com.google.slashb410.exgroup.ui.navi.DeveloperMessage;
import com.google.slashb410.exgroup.ui.write.QuickWriteActivity;
import com.google.slashb410.exgroup.util.U;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @BindView(R.id.profile_nick)
    TextView nick_profile;
    @BindView(R.id.seqAttendNum)
    TextView seqAttendNum;
    @BindView(R.id.bmi)
    TextView bmi;


    GridAdapter gridAdapter;
    ResGroupData resGroupData;

<<<<<<< HEAD
=======

>>>>>>> e17a066077aeee68413868be874540a616d561ed
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home2_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search_group) {
            U.getInstance().goNext(this, GroupSearchActivity.class, false, false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2_acrivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        //출석체크
        checkAttend();
        //프로필박스 세팅
//        setProfileBox();
        //그룹리스트 세팅
        setGroupList();

        String token = FirebaseInstanceId.getInstance().getToken();
//        Log.i("토큰 확인 : ", token);

        GridView gridView = (GridView) findViewById(R.id.group_grid);
//        GridAdapter gridAdapter = new GridAdapter(this, R.layout.group_card_view, ResGroupData.Result);
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

        // [ menu type ] 0:에러 1:체중 2:운동 3:식단
        scaleQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goQuickMenu(1);
            }
        });
        exerciseQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goQuickMenu(2);
            }
        });
        mealQuick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goQuickMenu(3);
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

    private void setGroupList() {
        Call<ResGroupList> resMe = NetSSL.getInstance().getGroupImpFactory().groupList();
        resMe.enqueue(new Callback<ResGroupList>() {
            @Override
            public void onResponse(Call<ResGroupList> call, Response<ResGroupList> response) {
                U.getInstance().myLog(response.body().getResultCode()+"");
            }

            @Override
            public void onFailure(Call<ResGroupList> call, Throwable t) {
                U.getInstance().myLog("접근실패 : "+t.toString());
            }
        });

    }

    private void setProfileBox() {
        Call<ResMe> resMe = NetSSL.getInstance().getMemberImpFactory().userMe();
        resMe.enqueue(new Callback<ResMe>() {
            @Override
            public void onResponse(Call<ResMe> call, Response<ResMe> response) {
                if(response.body().getNickname().equals("")){
                    U.getInstance().myLog("바디 널");
                }else {
                    nick_profile.setText(response.body().getNickname());
                    bmi.setText(response.body().getBMI());
                    seqAttendNum.setText(response.body().getSeqAttendNum());
                }
            }

            @Override
            public void onFailure(Call<ResMe> call, Throwable t) {
                U.getInstance().myLog("접근실패 : "+t.toString());
            }
        });

    }


    private void goQuickMenu(int i) {
        Intent intent = new Intent(this, QuickWriteActivity.class);
        intent.putExtra("menu", i);
        startActivity(intent);
    }

    private void checkAttend() {

        //1. 마지막 접속일자 가져오기
        String lastdate = getLastDate(this);

        //2. 오늘날짜 가져오기
        String[] todays = U.getInstance().currentYYmmDD();
        String today = todays[0] + "-" + todays[1] + "-" + todays[2];

        //#. 최초 접속 예외 처리
        if (lastdate == null) {
            U.getInstance().popSimpleDialog(null, this, null, "출석체크를 완료했습니다.");
            setLastDate(this, today);
<<<<<<< HEAD
        }else {
=======
        } else {

>>>>>>> e17a066077aeee68413868be874540a616d561ed
            SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
            Date today_date = null;
            Date lastdate_date = null;
            try {
                today_date = format.parse(today);
                lastdate_date = format.parse(lastdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //3. 마지막 접속일자와 오늘날짜가 다르다면 출석체크

            if (today_date != null && lastdate_date != null) {
                int compare = today_date.compareTo(lastdate_date);
                if (compare != 0) {
                    //마지막 접속일자가 오늘이 아니라면 출석체크

                    U.getInstance().popSimpleDialog(null, this, null, "출석체크를 완료했습니다.");

                }
            }
        }

        //4. 지금 날짜를 마지막 접속일자로 입력
        setLastDate(this, today);

    }

    private String getLastDate(Context context) {
        return StorageHelper.getInstance().getString(context, E.KEY.LASTDATE_KEY);
    }

    private void setLastDate(Context context, String today) {

        StorageHelper.getInstance().setString(context, E.KEY.LASTDATE_KEY, today);

    }

    @Override
    protected void onResume() {
        super.onResume();
        //    gridAdapter.notifyDataSetChanged();
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
                    String to       =   DeveloperMessage.editTextTo.getText().toString();
                    String subject  =   DeveloperMessage.editTextSubject.getText().toString();
                    String message  =   DeveloperMessage.editTextMessage.getText().toString();
                    Intent email    =   new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                    email.putExtra(Intent.EXTRA_SUBJECT, subject);
                    email.putExtra(Intent.EXTRA_TEXT, message);

                    //need this to prompts email client only
                    email.setType("message/rfc822");

                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                    Toast.makeText(getApplicationContext(), "메일 발송", Toast.LENGTH_SHORT).show();
                }
            });
//            aDialog.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
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

    @OnClick(R.id.profile_box)
    public void goMyPage() {
        U.getInstance().goNext(this, MyHomeActivity.class, false, false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            E.KEY.group_list.clear();
//            E.KEY.new_group.deleteGroupInfo();
            E.KEY.new_write.deleteWriteData();
            E.KEY.shotData.deleteShotData();
            this.finish();


        }
        return true;
    }
}
