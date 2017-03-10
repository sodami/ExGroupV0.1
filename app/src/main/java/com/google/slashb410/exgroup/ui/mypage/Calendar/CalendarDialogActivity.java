package com.google.slashb410.exgroup.ui.mypage.Calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.group.InnerCalendar;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarDialogActivity extends AppCompatActivity {

    ImageView calendarfood;
    ImageView calendarweigth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_dialog);

        Call<InnerCalendar> innerCalendar = NetSSL.getInstance().getMemberImpFactory().myCalendar();
        innerCalendar.enqueue(new Callback<InnerCalendar>() {
            @Override
            public void onResponse(Call<InnerCalendar> call, Response<InnerCalendar> response) {
                if (response.body().getUserPicUrl() == null) {
                    U.getInstance().myLog("onSelectedDayChange : Body is NULL");
                    return;
                } else {
                    U.getInstance().myLog("onSelectedDayChange : "+response.body().getUserPicUrl().toString());
                    final ImageView calendarfood = (ImageView) findViewById(R.id.calendarfood);
                    if (response.body().getUserPicUrl() != null)
                        Picasso.with(CalendarDialogActivity.this)
                                .load(response.body().getUserPicUrl())
                                .resize(50, 50)
                                .centerCrop()
                                .into(calendarfood);
                }
            }

            @Override
            public void onFailure(Call<InnerCalendar> call, Throwable t) {
                U.getInstance().myLog("onSelectedDayChange : " + t.toString());
            }
        });
    }
}
