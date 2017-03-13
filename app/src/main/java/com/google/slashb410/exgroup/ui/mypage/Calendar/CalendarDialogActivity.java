package com.google.slashb410.exgroup.ui.mypage.Calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.group.InnerCalendar;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarDialogActivity extends AppCompatActivity
{
    ImageView calendarfood;
    ImageView calendarweigth;
    TextView writeDate;
    TextView foodSummary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_dialog);

        calendarfood    = (ImageView)findViewById(R.id.calendarfood);
        calendarweigth  = (ImageView)findViewById(R.id.calendarweigth);
        writeDate       = (TextView)findViewById(R.id.today);
        foodSummary     = (TextView)findViewById(R.id.FoodSummary);

        setDialogBox();
    }


    //A_8. 마이페이지 캘린더에서 해당 날짜 게시글 보이기
    private void setDialogBox() {
        Log.i("SetDialog : ", "setDialogBox 진입");
        Call<InnerCalendar> MyCalendar = NetSSL.getInstance().getMemberImpFactory().myCalendar();
        MyCalendar.enqueue(new Callback<InnerCalendar>() {
            @Override
            public void onResponse(Call<InnerCalendar> call, Response<InnerCalendar> response) {
                if (response.isSuccessful()) {
                    if (response.body() == null) {
                        U.getInstance().myLog("setDialog Body is NULL : " + response.message());
                        return;
                    } else {
                        U.getInstance().myLog("InnerCalendar : " + response.body().toString());
                        // 작성 날짜
                        if (response.body().getWriteDate().toString() != null)
                            writeDate.setText(response.body().getWriteDate().toString() + "");
                        // 식단 사진
                        if (response.body().getBoardPicUrl().toString() != null)
                            Picasso.with(CalendarDialogActivity.this)
                                    .load(response.body().getBoardPicUrl().toString())
                                    .fit()
                                    .centerCrop()
                                    .into(calendarfood);
                        // 몸무게 사진
                        // ex -> getBoardPicUrl (게시판사진)
                        if (response.body().getBoardPicUrl().toString() != null)
                            Picasso.with(CalendarDialogActivity.this)
                                    .load(response.body().getBoardPicUrl().toString())
                                    .fit()
                                    .centerCrop()
                                    .into(calendarweigth);
                        // 한줄 요약
                        if (response.body().getSummary().toString() != null)
                            foodSummary.setText(response.body().getSummary().toString() +"");

//                        myData = new MyData(
//                                response.body().getData().getId(),
//                                response.body().getData().getUsername(),
//                                response.body().getData().getPassword(),
//                                response.body().getData().getNickname(),
//                                response.body().getData().getPicUrl(),
//                                response.body().getData().getWeight(),
//                                response.body().getData().getHeight(),
//                                response.body().getData().getBMI(),
//                                response.body().getData().getSeqAttendNum(),
//                                response.body().getData().getAge(),
//                                response.body().getData().getFacebookId(),
//                                response.body().getData().getActivation(),
//                                response.body().getData().getUtime(),
//                                response.body().getData().getCtime()
//                        );
                    }
                } else {
                    U.getInstance().myLog("setDialog is not successful : " + response.message());
                    if (response.body() == null) {
                        U.getInstance().myLog("setDialogBox Body is NULL");
                        return;
                    } else {
                        U.getInstance().myLog("InnerCalendar : " + response.body().toString());

                        // 작성 날짜
                        if (response.body().getWriteDate().toString() != null)
                            writeDate.setText(response.body().getWriteDate().toString() + "");
                        // 식단 사진
                        // ex -> getUserPic (www.daum.net)
                        if (response.body().getUserPicUrl().toString() != null)
                            Picasso.with(CalendarDialogActivity.this)
                                    .load(response.body().getUserPicUrl().toString())
                                    .fit()
                                    .centerCrop()
                                    .into(calendarfood);
                        // 몸무게 사진
                        // ex -> getBoardPicUrl (null)
                        if (response.body().getBoardPicUrl().toString() != null)
                            Picasso.with(CalendarDialogActivity.this)
                                    .load(response.body().getBoardPicUrl().toString())
                                    .fit()
                                    .centerCrop()
                                    .into(calendarweigth);
                        // 한줄 요약
                        if (response.body().getSummary().toString() != null)
                            foodSummary.setText(response.body().getSummary().toString() +"");
                    }
                }
            }
            @Override
            public void onFailure (Call < InnerCalendar > call, Throwable t){
                U.getInstance().myLog("접근실패 : " + t.toString());
            }
        });
    }
}
