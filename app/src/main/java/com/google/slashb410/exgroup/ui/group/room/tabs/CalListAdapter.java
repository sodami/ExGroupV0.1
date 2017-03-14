package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.CalendarListData;
import com.google.slashb410.exgroup.model.group.group.InnerCalendar;
import com.google.slashb410.exgroup.util.U;

import java.util.ArrayList;
import java.util.zip.Inflater;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Tacademy on 2017-02-07.
 */

public class CalListAdapter extends BaseAdapter {

    ArrayList<InnerCalendar> innerCalendar;
    Context context;

    public CalListAdapter(Context context, ArrayList<InnerCalendar> innerCalendar) {

        this.context = context;
        this.innerCalendar = innerCalendar;

    }

    @Override
    public int getCount() {
        return innerCalendar.size();
    }

    @Override
    public Object getItem(int position) {
        return innerCalendar.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListHolder holder = new ListHolder();

        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.cell_calendar_list, parent, false);
        }
        holder.menuImg = (ImageView) convertView.findViewById(R.id.menu_group_calendar_list);
        holder.nick = (TextView) convertView.findViewById(R.id.nickname_group_calendar_list);
        holder.date = (TextView) convertView.findViewById(R.id.dateNtime_group_calendar_list);
        holder.summary = (TextView) convertView.findViewById(R.id.summary_group_calendar_list);

        switch (innerCalendar.get(position).getCategoryNum()){
            case 1:
                holder.menuImg.setImageResource(R.drawable.scale_pink);
                break;
            case 2:
                holder.menuImg.setImageResource(R.drawable.exercise_pink);
                break;
            case 3:
                holder.menuImg.setImageResource(R.drawable.meal_pink);
                break;
        }

        holder.nick.setText(innerCalendar.get(position).getNickname());

        String customDate = U.getInstance().customDateNTime(innerCalendar.get(position).getWriteDate());
        holder.date.setText(customDate);
        holder.summary.setText(innerCalendar.get(position).getSummary());

        return convertView;
    }

    class ListHolder{

        ImageView menuImg;
        CircleImageView profile;
        TextView nick;
        TextView date;
        TextView summary;

    }
}