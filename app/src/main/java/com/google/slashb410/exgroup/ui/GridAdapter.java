package com.google.slashb410.exgroup.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;

/**
 * Created by Tacademy on 2017-02-01.
 */
class GridAdapter extends BaseAdapter {

    Context context;
    String[] groupName;
    int layout;
    LayoutInflater inflater;

    public GridAdapter(Context context, int layout, String[] groupName) {
        this.context = context;
        this.layout = layout;
        this.groupName = groupName;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return (groupName.length);
    }

    @Override
    public Object getItem(int position) {

        return groupName[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null)
            convertView = inflater.inflate(layout, null);

        TextView textView = (TextView) convertView.findViewById(R.id.group_name);
            textView.setText(groupName[position]);



        return convertView;

    }
}
