package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;

/**
 * Created by Tacademy on 2017-02-07.
 */

public class CalChildListHolder {

    ImageView menuImg;
    ImageView profileImg;
    TextView nickname;
    TextView dateNtime;
    TextView summary;


    public CalChildListHolder(View itemView) {

        menuImg = (ImageView) itemView.findViewById(R.id.menu_group_calendar_list);
        profileImg = (ImageView) itemView.findViewById(R.id.profile_group_calendar_list);
        nickname = (TextView) itemView.findViewById(R.id.nickname_group_calendar_list);
        dateNtime = (TextView) itemView.findViewById(R.id.dateNtime_group_calendar_list);
        summary = (TextView) itemView.findViewById(R.id.summary_group_calendar_list);
    }
}
