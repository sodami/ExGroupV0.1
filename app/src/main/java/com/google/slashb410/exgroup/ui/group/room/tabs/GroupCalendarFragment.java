package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.CalendarListData;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Tacademy on 2017-02-02.
 */

public class GroupCalendarFragment extends Fragment {

    ExpandableListView listView;
    CalExpandableListAdapter adapter;
    ArrayList<String> theDay;
    ArrayList<ArrayList<CalendarListData>> theDayActList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
//
        View view = inflater.inflate(R.layout.fragment_group_calendar, container, false);
        listView = (ExpandableListView) view.findViewById(R.id.group_cal_list);

        theDayActList = new ArrayList<>();

        ArrayList<CalendarListData> inData = new ArrayList<>();

        inData.add(new CalendarListData(0, "asdf", "슬비닉네임", "2017년 2월 7일 17시 49분", "3키로 감량!"));
        theDayActList.add(0, inData);

        theDay = new ArrayList<>();
        theDay.add("날짜를 선택하시면 활동내역을 볼 수 있습니다.");
        adapter = new CalExpandableListAdapter(getContext(), theDay, theDayActList);
        listView.setAdapter(adapter);

        CalendarPickerView calendar = (CalendarPickerView) view.findViewById(R.id.group_calendar);
        Date today = new Date();
        calendar.init(today, nextYear.getTime())
                .withSelectedDate(today);


        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                Date dateSelected = date;
                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
                theDay = new ArrayList<String>();
                theDay.add(transFormat.format(dateSelected));
              //  listView.setAdapter(new CalExpandableListAdapter(getContext(), theDay, null));
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

        return view;
    }
}
