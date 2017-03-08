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
    CalListViewHolder mainHolder;
    CalChildListHolder childListHolder;

    public CalExpandableListAdapter(Context context, ArrayList<String> mainList, ArrayList<ArrayList<CalendarListData>> childList) {
        this.mainList = mainList;
        this.childList = childList;
        this.inflater = LayoutInflater.from(context);

    }

    public void setNewData(ArrayList<String> _mainList, ArrayList<ArrayList<CalendarListData>> _childList){

        if(_mainList!=null) this.mainList = _mainList;
        if(_childList!=null) this.childList = _childList;

        notifyDataSetChanged();

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

        view = inflater.inflate(R.layout.cell_calendar_main_list, parent, false);

        if (view == null) {
            mainHolder = new CalListViewHolder(view);
            mainHolder.groupAct.setText(mainList.get(groupPosition));
        }

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
//

        if (view == null) {
            view = inflater.inflate(R.layout.cell_calendar_list, null);
        }
        childListHolder = new CalChildListHolder(view);

        switch (childList.get(0).get(childPosition).getBoardType() + "") {
            case "0":
                childListHolder.menuImg.setImageResource(R.drawable.scale_black);
                break;
            case "1":
                childListHolder.menuImg.setImageResource(R.drawable.exercise_black);
                break;
            case "2":
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
