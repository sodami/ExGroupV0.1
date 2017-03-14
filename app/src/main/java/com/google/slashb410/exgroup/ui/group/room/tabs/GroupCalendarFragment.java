package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andexert.calendarlistview.library.SimpleMonthAdapter;
import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.group.GroupData;
import com.google.slashb410.exgroup.model.group.group.InnerCalendar;
import com.google.slashb410.exgroup.ui.group.room.GroupHomeActivity;
import com.squareup.timessquare.CalendarPickerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Tacademy on 2017-02-02.
 */

public class GroupCalendarFragment extends Fragment implements com.andexert.calendarlistview.library.DatePickerController {

    ListView listView;
    CalListAdapter adapter;
    GroupData groupData;
    SimpleDateFormat format;
    SimpleDateFormat timeFormat;
    Date startDate;
    Date goalDate;

    ArrayList<InnerCalendar> innerCalendars;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        groupData = ((GroupHomeActivity) getActivity()).groupData;
        format = new SimpleDateFormat("yyyy-MM-dd");
        timeFormat = new SimpleDateFormat("HH시 mm분");

        try {
            startDate = format.parse(groupData.getStartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            goalDate = format.parse(groupData.getGoalDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(goalDate);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date goalDate_tommorow = cal.getTime();

        View view = inflater.inflate(R.layout.fragment_group_calendar, container, false);

//        adapter = new CalListAdapter(getContext(), innerCalendars);
//        listView.setAdapter(adapter);

        CalendarPickerView calendar = (CalendarPickerView) view.findViewById(R.id.group_calendar);
        Date today = new Date();
        calendar.init(startDate, goalDate_tommorow)
                .withSelectedDate(today);

        ArrayList<Date> highlightDateSet = new ArrayList<>();
        highlightDateSet.add(startDate);
        highlightDateSet.add(goalDate);

        calendar.highlightDates(highlightDateSet);

        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                String dateSelected = format.format(date);
                Intent intent = new Intent(getActivity(), GroupCalListDialogActivity.class);
                intent.putExtra("date", dateSelected);
                intent.putExtra("groupData", groupData);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);
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
