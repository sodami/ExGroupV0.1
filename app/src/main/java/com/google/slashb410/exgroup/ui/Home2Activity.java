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
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.slashb410.exgroup.GridAdapter;
import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.db.StorageHelper;
import com.google.slashb410.exgroup.model.group.group.GroupData;
import com.google.slashb410.exgroup.model.group.group.ResGroupList;
import com.google.slashb410.exgroup.model.group.home.ResAttend;
import com.google.slashb410.exgroup.model.group.home.ResLogout;
import com.google.slashb410.exgroup.model.group.home.ResMe;
import com.google.slashb410.exgroup.model.group.home.ResSessionOut;
import com.google.slashb410.exgroup.model.group.home.Ticket;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.ui.group.search.GroupSearchActivity;
import com.google.slashb410.exgroup.ui.mypage.MyHomeActivity;
import com.google.slashb410.exgroup.ui.write.QuickWriteActivity;
import com.google.slashb410.exgroup.util.U;

import java.util.ArrayList;

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

    GridView gridView;
    GridAdapter gridAdapter;
    ResGroupList resGroupList;

    ArrayList actGroups;
    ArrayList actTitles;

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

        String[] todays = U.getInstance().currentYYmmDD();
        String today = todays[0] + "-" + todays[1] + "0" + todays[2];

        actGroups = new ArrayList();
        actTitles = new ArrayList();

        //유효한 티켓이 없다면 출석체크
        if (!hasValidTicket(today)) {
            checkAttend(today);
        }

        //프로필박스 세팅
        setProfileBox();
        //그룹리스트 세팅
        setGroupList();

        String token = FirebaseInstanceId.getInstance().getToken();
//        Log.i("토큰 확인 : ", token);

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
        //this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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
                if (response.body() == null) {
                    U.getInstance().myLog("setGroupList body is NULL");
                    return;
                }
                if (response.body().getResultCode() == 1) {

                    resGroupList = response.body();
                    ArrayList<GroupData> actRst = resGroupList.getResult().getActRst();
                    ArrayList<GroupData> waitRst = resGroupList.getResult().getWaitRst();
                    ArrayList<GroupData> unActRst = resGroupList.getResult().getUnActRst();

                    U.getInstance().myLog("actGroup size : " + actRst.size());
                    U.getInstance().myLog("waitGroup size : "+waitRst.size());
                    U.getInstance().myLog("unActGroup size : " + unActRst.size());

                    for(int i =0; i<actRst.size();++i){
                        actGroups.add(actRst.get(i).getGroup_id());
                        actTitles.add(actRst.get(i).getGroupTitle());
                    }
                    gridAdapter = new GridAdapter(Home2Activity.this, R.layout.group_card_view, actRst, waitRst, unActRst);
                    gridView = (GridView) findViewById(R.id.group_grid);
                    gridView.setAdapter(gridAdapter);

                } else {
                    U.getInstance().myLog("setGroupList resultCode : " + response.body().getResultCode());
                }
            }

            @Override
            public void onFailure(Call<ResGroupList> call, Throwable t) {
                U.getInstance().myLog("접근실패 : " + t.toString());
            }
        });

    }

    private void setProfileBox() {
        Call<ResMe> resMe = NetSSL.getInstance().getMemberImpFactory().userMe();
        resMe.enqueue(new Callback<ResMe>() {
            @Override
            public void onResponse(Call<ResMe> call, Response<ResMe> response) {
                if (response.body().getData() == null) {
                    U.getInstance().myLog("setProfileBox Body is NULL");
                } else {
                    U.getInstance().myLog("ResMe : "+response.body().getData().toString());
                    if (response.body().getData().getNickname() != null) nick_profile.setText(response.body().getData().getNickname());
                    if (response.body().getData().getBMI() != null) bmi.setText(response.body().getData().getBMI());
                    if (response.body().getData().getSeqAttendNum() != 0) seqAttendNum.setText(response.body().getData().getSeqAttendNum());
                }
            }

            @Override
            public void onFailure(Call<ResMe> call, Throwable t) {
                U.getInstance().myLog("접근실패 : " + t.toString());
            }
        });
    }


    private void goQuickMenu(int i) {
        Intent intent = new Intent(this, QuickWriteActivity.class);
        intent.putExtra("menu", i);

        intent.putExtra("actGroups", actGroups);
        String[] actTitles_str = new String[actTitles.size()];
        for(int j=0; j<actTitles.size();++j){
            actTitles_str[j]= (String) actTitles.get(j);
        }
        intent.putExtra("actTitles", actTitles_str);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void checkAttend(String today) {

        U.getInstance().myLog("출석 요청을 시작합니다");

        Call<ResAttend> resAttend = NetSSL.getInstance().getMemberImpFactory().attend(today);
        resAttend.enqueue(new Callback<ResAttend>() {
            @Override
            public void onResponse(Call<ResAttend> call, Response<ResAttend> response) {
                if (response.body() != null) {
                    if(response.body().getResultCode()==1){
                        Gson gson = new Gson();
                        String ticket_json = gson.toJson(response.body().getTicket());
                        StorageHelper.getInstance().setString(getApplicationContext(), "ticket", ticket_json);

                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                        builder.setMessage("출석 체크 완료!")
                                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                }).show();
                    }
                } else {
                    U.getInstance().myLog("ResAttend Body is NULL");
                }
            }

            @Override
            public void onFailure(Call<ResAttend> call, Throwable t) {
                U.getInstance().myLog("접근실패 : " + t.toString());
            }
        });
    }


    private boolean hasValidTicket(String today) {

        //티켓이 존재하지 않는다. > false
        if(StorageHelper.getInstance().getString(getApplicationContext(), "ticket")==null) {
            U.getInstance().myLog("티켓이 없네요");
            return false;
        }

        String ticket_str = StorageHelper.getInstance().getString(getApplicationContext(), "ticket");
        Gson gson = new Gson();
        Ticket ticket = gson.fromJson(ticket_str, Ticket.class);

        //티켓이 존재하지만 오늘과 같으면 true 다르면 false
        return (ticket.getTicketPeriod().equals(today));

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

        if (id == R.id.nav_camera) { // 누르면 로그아웃
            onLogout();
            Intent intent = new Intent(this, EnterActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_gallery) { // 누르면 출석체크 푸쉬 on/off

        } else if (id == R.id.nav_slideshow) { // 누르면 운동, 식당 인증 푸쉬 on/off

//        } else if(id == R.id.nav_session) { // 누르면 앱 연결 해제하기(탈퇴)
//            Context mContext = getApplicationContext();
//            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
//            View layout = inflater.inflate(R.layout.activity_session_dialog, (ViewGroup) findViewById(R.id.sessionpopup));
//            AlertDialog.Builder aDialog = new AlertDialog.Builder(Home2Activity.this);
//            aDialog.setView(layout);
//            aDialog.setNegativeButton("확인", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    onSessionout();
//                    Toast.makeText(getApplicationContext(), "탈퇴버튼 클릭", Toast.LENGTH_SHORT).show();
//                }
//            });
//            AlertDialog ad = aDialog.create();
//            ad.show();
        } else if (id == R.id.nav_manage) { // 누르면 개발자에게 문의하기
        } else if(id == R.id.nav_session) { // 누르면 앱 연결 해제하기(탈퇴)
            Context mContext = getApplicationContext();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.activity_session_dialog, (ViewGroup) findViewById(R.id.sessionpopup));
            AlertDialog.Builder aDialog = new AlertDialog.Builder(Home2Activity.this);
            aDialog.setView(layout);
            aDialog.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    onSessionout();
                    Toast.makeText(getApplicationContext(), "탈퇴버튼 클릭", Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog ad = aDialog.create();
            ad.show();
        } else if (id == R.id.nav_manage) {
            // 누르면 개발자에게 문의하기
            Context mContext = getApplicationContext();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.activity_developer_message, (ViewGroup) findViewById(R.id.popup));
            final EditText title = (EditText) layout.findViewById(R.id.editText1);
            final EditText sendto = (EditText) layout.findViewById(R.id.editText2);
            final EditText content = (EditText) layout.findViewById(R.id.editText3);
            // 알람창 띄우기
            AlertDialog.Builder aDialog = new AlertDialog.Builder(Home2Activity.this);
            // dialog.xml 파일을 뷰로 셋팅
            aDialog.setView(layout);
            // send 버튼 클릭 시 이벤트
            aDialog.setNegativeButton("send", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    String to = title.getText().toString();
                    String subject = sendto.getText().toString();
                    String message = content.getText().toString();
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                    email.putExtra(Intent.EXTRA_SUBJECT, subject);
                    email.putExtra(Intent.EXTRA_TEXT, message);

                    //need this to prompts email client only
                    email.setType("message/rfc822");

                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                    Toast.makeText(getApplicationContext(), "메일 발송이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            //팝업창 만들기
            AlertDialog ad = aDialog.create();
            ad.show();
        } else if (id == R.id.nav_share) {
            // 친구에게 공유하기
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // 회원 탈퇴
    private void onSessionout()
    {
        Call<ResSessionOut> res =
                NetSSL.getInstance().getMemberImpFactory().sessionout(); //전문에 있는 양식 순서대로
        res.enqueue(new Callback<ResSessionOut>() { //enqueue가 callback오니까
            @Override
            public void onResponse(Call<ResSessionOut> call, Response<ResSessionOut> response) {
                if (response != null) {
                    if (response.body() != null) {
                        if (response.body().getResultCode() == 1) {
                            Log.i("RF", "회원 탈퇴 성공" + response.body().getResultCode());
                        } else {
                            Log.i("RF", "1응답 데이터 구조 오류 구조값이 달라서 JSON 자동 파싱 처리가 않됨");
                        }
                    } else {
                        Log.i("RF", "2로그아웃실패" + response.code()); //통신은 들어갔는데 오류
                    }
                } else {
                    Log.i("RF", "3응답 데이터 오류");
                }
                // Log.i("RF", "가입실패   //" + response);
            }

            @Override
            public void onFailure(Call<ResSessionOut> call, Throwable t) {
                Log.i("RF", "ERR" + t.getMessage());
            }
        });
    }

    //로그아웃
    public void onLogout() {
        Call<ResLogout> res =
                NetSSL.getInstance().getMemberImpFactory().logout(); //전문에 있는 양식 순서대로
        res.enqueue(new Callback<ResLogout>() { //enqueue가 callback오니까
            @Override
            public void onResponse(Call<ResLogout> call, Response<ResLogout> response) {
                if (response != null) {
                    if (response.body() != null) {
                        if (response.body().getReusltCode() == 1) {
                            Log.i("RF", "로그아웃성공" + response.body().getMessage());
                        } else {
                            Log.i("RF", "1응답 데이터 구조 오류 구조값이 달라서 JSON 자동 파싱 처리가 않됨");
                        }
                    } else {
                        Log.i("RF", "2로그아웃실패" + response.code()); //통신은 들어갔는데 오류
                    }
                } else {
                    Log.i("RF", "3응답 데이터 오류");
                }
                // Log.i("RF", "가입실패   //" + response);
            }

            @Override
            public void onFailure(Call<ResLogout> call, Throwable t) { //통신 자체 실패
                Log.i("RF", "ERR" + t.getMessage());
            }
        });
    }

    @OnClick(R.id.profile_box)
    public void goMyPage() {
        U.getInstance().goNext(this, MyHomeActivity.class, false, false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();

        }
        return true;
    }

}
