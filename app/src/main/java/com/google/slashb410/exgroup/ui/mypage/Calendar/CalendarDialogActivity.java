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
                        if (response.body().getResult() != null) //toString() != null)
                            writeDate.setText(response.body().getResult().toString());
                        // 식단 사진
                        if (response.body().getResult() != null)
                            Picasso.with(CalendarDialogActivity.this)
                                    .load(response.body().getResult().toString())
                                    .fit()
                                    .centerCrop()
                                    .into(calendarfood);
                        // 몸무게 사진
                        // ex -> getBoardPicUrl (게시판사진)
                        if (response.body().getResult().toString() != null)
                            Picasso.with(CalendarDialogActivity.this)
                                    .load(response.body().getResult().toString())
                                    .fit()
                                    .centerCrop()
                                    .into(calendarweigth);
                        // 한줄 요약
                        if (response.body().getResult() != null)
                            foodSummary.setText(response.body().getResult().toString() +"");
                    }
                } else {
                    U.getInstance().myLog("setDialog is not successful : " + response.message());
                    if (response.body() == null) {
                        U.getInstance().myLog("setDialogBox Body is NULL");
                        return;
                    } else {
                        U.getInstance().myLog("InnerCalendar : " + response.body().toString());
                        // 작성 날짜
                        if (response.body().getResult() != null) //toString() != null)
                            writeDate.setText(response.body().getResult().toString());
                        // 식단 사진
                        if (response.body().getResult() != null)
                            Picasso.with(CalendarDialogActivity.this)
                                    .load(response.body().getResult().toString())
                                    .fit()
                                    .centerCrop()
                                    .into(calendarfood);
                        // 몸무게 사진
                        // ex -> getBoardPicUrl (게시판사진)
                        if (response.body().getResult().toString() != null)
                            Picasso.with(CalendarDialogActivity.this)
                                    .load(response.body().getResult().toString())
                                    .fit()
                                    .centerCrop()
                                    .into(calendarweigth);
                        // 한줄 요약
                        if (response.body().getResult() != null)
                            foodSummary.setText(response.body().getResult().toString() +"");
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
