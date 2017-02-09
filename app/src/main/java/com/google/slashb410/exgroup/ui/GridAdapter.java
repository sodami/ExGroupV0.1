package com.google.slashb410.exgroup.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.db.FireBaseStorageHelper;
import com.google.slashb410.exgroup.model.group.GroupInfo;
import com.google.slashb410.exgroup.ui.group.create.GroupAddActivity;
import com.google.slashb410.exgroup.ui.group.room.GroupHomeActivity;
import com.google.slashb410.exgroup.util.U;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-01.
 */
class GridAdapter extends BaseAdapter {

    Context context;
    ArrayList<GroupInfo> groupInfos;
    int layout;
    LayoutInflater inflater;

    public GridAdapter(Context context, int layout, ArrayList<GroupInfo> groupInfos) {
        this.context = context;
        this.layout = layout;
        this.groupInfos = groupInfos;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return (groupInfos.size() + 1);
    }

    @Override
    public Object getItem(int position) {

        if (groupInfos.size() == position) return null;
        return groupInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(layout, null);

        TextView textView;
        TextView textView2;
        ImageView imageView;
        CardView cardView;

        if (position == E.KEY.GROUP_MAX) {
            cardView = (CardView) convertView.findViewById(R.id.group_add_cardview);
            cardView.setVisibility(View.VISIBLE);
            cardView.setClickable(false);
            cardView.setBackgroundResource(R.color.babyPink);
            imageView = (ImageView) convertView.findViewById(R.id.group_add_img);
            imageView.setVisibility(View.GONE);
            textView = (TextView) convertView.findViewById(R.id.group_addTxt);
            textView.setText("그룹은 최대 5개까지 소속될 수 있습니다.");
            textView.setTextColor(Color.WHITE);


        } else if (position == groupInfos.size()) {
            cardView = (CardView) convertView.findViewById(R.id.group_add_cardview);
            cardView.setVisibility(View.VISIBLE);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    U.getInstance().goNext(v.getContext(), GroupAddActivity.class, false);
                }
            });
        } else {
            cardView = (CardView) convertView.findViewById(R.id.group_cardview);
            textView = (TextView) convertView.findViewById(R.id.group_name_card);
            imageView = (ImageView) convertView.findViewById(R.id.group_cardImg);
            textView2 = (TextView) convertView.findViewById(R.id.group_term);

            textView.setText(groupInfos.get(position).getGroupName());
            textView2.setText(groupInfos.get(position).getTerm()+"");

            FireBaseStorageHelper.getInstance().getImage(convertView.getContext(), groupInfos.get(position).getGroupImgPath(), imageView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    U.getInstance().goNext(v.getContext(), GroupHomeActivity.class, false);
                }
            });
        }


        return convertView;

    }
}
