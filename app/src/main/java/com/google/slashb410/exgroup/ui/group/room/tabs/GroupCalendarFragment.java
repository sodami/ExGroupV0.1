package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.andexert.calendarlistview.library.DayPickerView;
import com.andexert.calendarlistview.library.SimpleMonthAdapter;
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

public class GroupCalendarFragment extends Fragment implements com.andexert.calendarlistview.library.DatePickerController {

    ExpandableListView listView;
    CalExpandableListAdapter adapter;
    ArrayList<String> theDay;
    ArrayList<ArrayList<CalendarListData>> theDayActList;
    DayPickerView dayPickerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.MONTH, 1);

        View view = inflater.inflate(R.layout.fragment_group_calendar, container, false);
        listView = (ExpandableListView) view.findViewById(R.id.group_cal_list);

        theDayActList = new ArrayList<>();

        ArrayList<CalendarListData> inData = new ArrayList<>();

        inData.add(new CalendarListData(1, "asdf", "슬비닉네임", "2017년 2월 7일 17시 49분", "1시간동안 15키로 자전거!"));
        theDayActList.add(0, inData);

        theDay = new ArrayList<>();
        theDay.add("날짜를 선택하시면 활동내역을 볼 수 있습니다.");
        adapter = new CalExpandableListAdapter(getContext(), theDay, theDayActList);
        listView.setAdapter(adapter);

//        dayPickerView = (DayPickerView) view.findViewById(R.id.pickerView);
//        dayPickerView.setController(this);

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
                theDay.add(0, transFormat.format(dateSelected));
                adapter.setNewData(theDay, null);
                listView.invalidateViews();
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

        return view;
    }

    @Override
    public int getMaxYear() {
        return 2015;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {
        Log.e("Day Selected", day + " / " + month + " / " + year);
    }

    @Override
    public void onDateRangeSelected(SimpleMonthAdapter.SelectedDays<SimpleMonthAdapter.CalendarDay> selectedDays) {
        Log.e("Date range selected", selectedDays.getFirst().toString() + " --> " + selectedDays.getLast().toString());
    }
}
