package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.CalendarListData;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-07.
 */

public class CalExpandableListAdapter extends BaseExpandableListAdapter {

    ArrayList<String> mainList;
    ArrayList<ArrayList<CalendarListData>> childList;
    LayoutInflater inflater;
    CalListViewHolder viewHolder;
    CalChildListHolder childListHolder;

    public CalExpandableListAdapter(Context context, ArrayList<String> mainList, ArrayList<ArrayList<CalendarListData>> childList) {
        this.mainList = mainList;
        this.childList = childList;
        this.inflater = LayoutInflater.from(context);

    }

    @Override
    public int getGroupCount() {
        return mainList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mainList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        viewHolder = new CalListViewHolder(view);
        view = inflater.inflate(R.layout.cell_calendar_main_list, parent, false);


        if(view==null){
            viewHolder.groupAct.setText(mainList.get(groupPosition));
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        childListHolder = new CalChildListHolder(view);
        if(view==null){
            view = inflater.inflate(R.layout.cell_calendar_list, null);
        }

        if(childList.get(0).get(childPosition)==null) return view;

        switch (childList.get(0).get(childPosition).getBoardType()+"") {
            case "0" :
                childListHolder.menuImg.setImageResource(R.drawable.scale_black);
                break;
            case "1" :
                childListHolder.menuImg.setImageResource(R.drawable.exercise_black);
                break;
            case "2" :
                childListHolder.menuImg.setImageResource(R.drawable.meal_black);
                break;
        }



        //childListHolder.profileImg.setImageResource();
        childListHolder.nickname.setText(childList.get(groupPosition).get(childPosition).getNickname());
        childListHolder.dateNtime.setText(childList.get(groupPosition).get(childPosition).getDateNtime());
        childListHolder.summary.setText(childList.get(groupPosition).get(childPosition).getSummary());

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
