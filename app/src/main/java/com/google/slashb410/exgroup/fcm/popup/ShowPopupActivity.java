//package com.google.slashb410.exgroup.fcm.popup;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.TextView;
//
//import com.google.slashb410.exgroup.R;
//import com.google.slashb410.exgroup.ui.Home2Activity;
//
///**
// *  메시지가 오면 단독으로 뜬다(폰이 꺼져 있어도 깨워서 뜬다)
// */
//public class ShowPopupActivity extends AppCompatActivity
//{
//    // 백키 무시
//    @Override
//    public void onBackPressed() {
//        //super.onBackPressed();
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        // 푸시 메시지 획득
//        String msg = getIntent().getStringExtra("FCM");
//        // 윈도우 설정 : 잠겨있어도 보이고, 화면을 유지하고, 뒤를 블러처리하고
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
//        | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON );
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
//                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
//        // 등장및 퇴장 애니메이션
//        overridePendingTransition(R.anim.start_enter, R.anim.start_exit);
//        // 화면설정
//        setContentView(R.layout.activity_show_popup);
//        // 버튼 이벤트 처리
//        TextView content = (TextView)findViewById(R.id.content);
//        content.setText(msg);
//    }
//    // 앱으로 이동
//    public void onOK(View view){
//        Intent intent = new Intent(this, Home2Activity.class);
//        // 푸시 메시지 전달
//        startActivity(intent);
//        finish();
//    }
//    // 창만 닫고 끝
//    public void onClose(View view){
//        finish();
//        //System.exit(0);
//    }
//    @Override
//    public void finish() {
//        // 닫기 애니메이션
//        overridePendingTransition(R.anim.end_enter, R.anim.end_exit);
//        super.finish();
//    }
//}
