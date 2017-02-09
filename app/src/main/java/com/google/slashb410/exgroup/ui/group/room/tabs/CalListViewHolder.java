package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.view.View;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;

/**
 * Created by Tacademy on 2017-02-07.
 */
public class CalListViewHolder  {

    TextView groupAct;


    public CalListViewHolder(View itemView) {

        groupAct = (TextView) itemView.findViewById(R.id.date_group_activity);
    }
}
