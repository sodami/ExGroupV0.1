package com.google.slashb410.exgroup.ui.mypage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.MyData;
import com.google.slashb410.exgroup.model.group.home.ResMe;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.ui.Home2Activity;
import com.google.slashb410.exgroup.util.U;
import com.miguelbcr.ui.rx_paparazzo.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo.entities.size.SmallSize;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyHomeActivity extends Activity {
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView profile_change;
    SweetAlertDialog alert;
    TextView nickname;      // 닉네임 보여짐
    CalendarView cal;
    ImageButton myBack;
    Button      myCancel;
    Bundle bundle;
    Student student;

    MyData myData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);

        //프로필 데이터 불러오기
        Intent intent = getIntent();
        myData = (MyData) intent.getSerializableExtra("myData");

        myCancel        = (Button) findViewById(R.id.my_cancel);
        myBack          = (ImageButton)findViewById(R.id.my_back);
        profile_change  = (ImageView) findViewById(R.id.profile_change);
        // profile_change.bringToFront();
        nickname        = (TextView) findViewById(R.id.resultMyName);      // 닉네임

        setProfile();


        // 2017. 02. 01
        Resources resource = getResources();
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        TabHost.TabSpec spec;
        tabHost.setup();
        spec = tabHost.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("", resource.getDrawable(R.drawable.calendar_white_resized));
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("", resource.getDrawable(R.drawable.chart_white_resized));
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);



        LineChart lineChart = (LineChart) findViewById(R.id.chart);
        ArrayList<Entry> entries = new ArrayList<Entry>();

        entries.add(new Entry(65.0f, 0));
        entries.add(new Entry(64.5f, 1));
        entries.add(new Entry(64.0f, 2));
        entries.add(new Entry(60.0f, 3));
        entries.add(new Entry(59.0f, 4));
        entries.add(new Entry(58.5f, 5));
        entries.add(new Entry(57.8f, 6));

        LineDataSet dataSet = new LineDataSet(entries, "슬비꺼");
        LineDataSet dataSet2 = new LineDataSet(entries, "소담이꺼");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("1일차");
        labels.add("2일차");
        labels.add("3일차");
        labels.add("4일차");
        labels.add("5일차");
        labels.add("6일차");
        labels.add("7일차");

        LineData data = new LineData(labels, dataSet);
        LineData data2 = new LineData(labels, dataSet2);
        lineChart.setData(data);
        lineChart.setData(data2);

        // individual Calendar 2017. 02. 17
        cal = (CalendarView) findViewById(R.id.calendarView1);
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() { // 캘린더 클릭 시 플로팅
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Log.i("onSelectedDayChange", "onSelectedDayChange 진입");
                Context mContext = getApplicationContext();
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.activity_calendar_dialog, (ViewGroup) findViewById(R.id.activity_calendar_dialog));
                AlertDialog.Builder aDialog = new AlertDialog.Builder(MyHomeActivity.this);
                aDialog.setView(layout);
                aDialog.setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        onStart();
                    }
                });
                AlertDialog ad = aDialog.create();
                ad.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onMyBack(View view) {
        onBackPressed();
    }

    public void onMySubmit(View view) {
        Intent intent = new Intent(MyHomeActivity.this, Home2Activity.class);
        intent.putExtra("name", "홍길동");
        startActivity(intent);
    }

    // profile  2017. 02. 01
    public void onPhoto(View view) {
        alert =
                new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                        .setTitleText("사진 선택")
                        .setContentText("사진 선택할 방법을 고르세요")
                        .setConfirmText("카메라")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                onCamera();
                            }
                        })
                        .setCancelText("포토앨범")
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                onGallery();
                            }
                        });
        alert.setCancelable(true);
        alert.show();
    }

    public void onCamera() {
        // 크롬작업을 하기 위해 옵션 설정(편집)
        UCrop.Options options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        options.setMaxBitmapSize(1024 * 1024 * 1); // 2MB 언더

        RxPaparazzo.takeImage(this)
                .size(new SmallSize())
                .crop(options)
                .useInternalStorage()
                .usingCamera()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    // See response.resultCode() doc
                    if (response.resultCode() != RESULT_OK) {
                        // response.targetUI().showUserCanceled();
                        return;
                    }
                    loadImage(response.data());
                });
    }

    public void onGallery() {
        RxPaparazzo.takeImage(this)
                .usingGallery()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    // See response.resultCode() doc
                    if (response.resultCode() != RESULT_OK) {
                        // response.targetUI().showUserCanceled();
                        return;
                    }
                    loadImage(response.data());
                });
    }

    public void loadImage(String path) {
        alert.dismissWithAnimation();
        // 이미지 뷰에 이미지를 세팅
        String url = "file://" + path;
        Picasso.with(this).setLoggingEnabled(true);
        Picasso.with(this).setIndicatorsEnabled(true);
        Picasso.with(this).invalidate(url);
        Picasso.with(this).load(url).into(profile_change);
    }

    // 2017.02.06 앨범에서 사진 하나 선택했을 때 result를 받아서 비트맵으로 변경 후 프로필에 적용
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 100:
                if (resultCode == RESULT_OK) {
                    try {
                        Uri selectedImage = imageReturnedIntent.getData();
                        InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                        Bitmap bitmapSelectedImage = BitmapFactory.decodeStream(imageStream);
                        profile_change.setImageBitmap(bitmapSelectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    public void onClick() {
        Intent intent = new Intent(MyHomeActivity.this, DetailInfo.class);
        startActivity(intent);
    }

    private void setProfile() {

        if (myData.getNickname() != null) nickname.setText(myData.getNickname());
        if(myData.getPicUrl()!=null) Picasso.with(this).load(myData.getPicUrl()).fit().centerCrop().into(profile_change);

    }
}
