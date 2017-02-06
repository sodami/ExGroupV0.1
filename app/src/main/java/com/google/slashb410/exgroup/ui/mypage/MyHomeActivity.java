package com.google.slashb410.exgroup.ui.mypage;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;

import com.google.slashb410.exgroup.R;
import com.miguelbcr.ui.rx_paparazzo.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo.entities.size.SmallSize;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyHomeActivity extends AppCompatActivity {

    ImageView profile_change;
    SweetAlertDialog alert;
    private ViewGroup layoutGraphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);
        layoutGraphView = (ViewGroup) findViewById(R.id.tab1);

        // 2017. 02. 01
        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        profile_change = (ImageView) findViewById(R.id.profile_change);
        tabHost.setup();


        TabHost.TabSpec spec = tabHost.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("BMI");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Calendar");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tag3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Planet");
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);

        setLineGraph();
    }

    //  2017. 02. 01
    public void onPhoto(View view)
    {
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
    public void onCamera()
    {
        // 크롬작업을 하기 위해 옵션 설정(편집)
        UCrop.Options options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        options.setMaxBitmapSize(1024*1024*1); // 2MB 언더

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
    public void onGallery()
    {
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
    public void loadImage( String path )
    {
        alert.dismissWithAnimation();
        // 이미지 뷰에 이미지를 세팅
        String url = "file://" + path;
        Picasso.with(this).setLoggingEnabled(true);
        Picasso.with(this).setIndicatorsEnabled(true);
        Picasso.with(this).invalidate(url);
        Picasso.with(this).load(url).into(profile_change);
    }

    // 2017.02.05
    public void setLineGraph()
    {
        //all setting
        LineGraphVO vo = makeLineGraphAllSetting();

        //default setting
//		LineGraphVO vo = makeLineGraphDefaultSetting();

        layoutGraphView.addView(new LineGraphView(this, vo));
    }
    public LineGraphVO makeLineGraphAllSetting()
    {
        //BASIC LAYOUT SETTING
        //padding
        int paddingBottom 	= LineGraphVO.DEFAULT_PADDING;
        int paddingTop 		= LineGraphVO.DEFAULT_PADDING;
        int paddingLeft 	= LineGraphVO.DEFAULT_PADDING;
        int paddingRight 	= LineGraphVO.DEFAULT_PADDING;

        //graph margin
        int marginTop 		= LineGraphVO.DEFAULT_MARGIN_TOP;
        int marginRight 	= LineGraphVO.DEFAULT_MARGIN_RIGHT;

        //max value
        int maxValue 		= LineGraphVO.DEFAULT_MAX_VALUE;

        //increment
        int increment 		= LineGraphVO.DEFAULT_INCREMENT;

        //GRAPH SETTING
        String[] legendArr 	= {"MON","TUE","WED","THU","FRI","SAT","SUN"};
        float[] graph1 		= {55,57,48,53,50,57,64};
//        float[] graph2 		= {000,100,200,100,200};
//        float[] graph3 		= {200,500,300,400,000};

        List<LineGraph> arrGraph 		= new ArrayList<LineGraph>();

        arrGraph.add(new LineGraph("android", 0xaa66ff33, graph1, R.mipmap.ic_launcher));
        // 친구랑 경쟁 몸무게
//        arrGraph.add(new LineGraph("ios", 0xaa00ffff, graph2));
//        arrGraph.add(new LineGraph("tizen", 0xaaff0066, graph3));

        LineGraphVO vo = new LineGraphVO(
                paddingBottom, paddingTop, paddingLeft, paddingRight,
                marginTop, marginRight, maxValue, increment, legendArr, arrGraph);

        //set animation
        vo.setAnimation(new GraphAnimation(GraphAnimation.DEFAULT_DURATION, GraphAnimation.DEFAULT_DURATION));
        //set graph name box
        vo.setGraphNameBox(new GraphNameBox());
        //set draw graph region
//		vo.setDrawRegion(true);

        //use icon
//		arrGraph.add(new Graph(0xaa66ff33, graph1, R.drawable.icon1));
//		arrGraph.add(new Graph(0xaa00ffff, graph2, R.drawable.icon2));
//		arrGraph.add(new Graph(0xaaff0066, graph3, R.drawable.icon3));

//		LineGraphVO vo = new LineGraphVO(
//				paddingBottom, paddingTop, paddingLeft, paddingRight,
//				marginTop, marginRight, maxValue, increment, legendArr, arrGraph, R.drawable.bg);
        return vo;
    }

//    // 2017.02.06
//    // 앨범에서 사진 하나 선택했을 때 result를 받아서 비트맵으로 변경 후 프로필에 적용
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent)
//    {
//        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
//        switch (requestCode) {
//            case 100:
//                if (resultCode == RESULT_OK) {
//                    try {
//                        Uri selectedImage = imageReturnedIntent.getData();
//                        InputStream imageStream = getContentResolver().openInputStream(selectedImage);
//                        Bitmap bitmapSelectedImage = BitmapFactory.decodeStream(imageStream);
//                        profile_change.setImageBitmap(bitmapSelectedImage);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//        }
//    }
}

