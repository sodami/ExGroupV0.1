package com.google.slashb410.exgroup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.model.group.group.GroupData;
import com.google.slashb410.exgroup.ui.group.create.GroupAddActivity;
import com.google.slashb410.exgroup.ui.group.create.GroupWaitingActivity;
import com.google.slashb410.exgroup.ui.group.room.GroupHomeActivity;
import com.google.slashb410.exgroup.util.U;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-10.
 */

public class GridAdapter extends BaseAdapter {

    Context context;
    ArrayList<GroupData> actGroup;
    ArrayList<GroupData> waitGroup;
    ArrayList<GroupData> unActGroup;

    int layout;
    LayoutInflater inflater;

    public GridAdapter(Context context, int layout, ArrayList<GroupData> actGroup, ArrayList<GroupData> waitGroup, ArrayList<GroupData> unActGroup) {
        this.context = context;
        this.layout = layout;
        this.actGroup = actGroup;
        this.waitGroup = waitGroup;
        this.unActGroup = unActGroup;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(actGroup.size()==0&&unActGroup.size()==0&&waitGroup.size()==0) return 0;
        return (actGroup.size() + waitGroup.size() + unActGroup.size() + 1);
    }

    @Override
    public Object getItem(int position) {
        int startPosition= 0;
        if(position<actGroup.size()){
            startPosition+=actGroup.size();
            return actGroup.get(position);
        }else if(position>=startPosition&&position<actGroup.size()+waitGroup.size()){
            startPosition+=waitGroup.size();
            position = position-actGroup.size();
            return waitGroup.get(position);
        }else if(position==actGroup.size()+waitGroup.size()){
            return null;
        }else{
            position = position - (actGroup.size()+waitGroup.size()+1);
            return unActGroup.get(position);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(layout, null);
        //actGroup+waitGroup 크기가 GROUP_MAX와 같으면 안내카드
        //방장이 있다면 라벨링, add카드 -> 안내카드
        //그외는 포지션에 따라 setText

        TextView textView;
        TextView textView2;
        ImageView imageView;
        CardView addCardView = (CardView) convertView.findViewById(R.id.group_add_cardview);
        CardView groupCardview = (CardView) convertView.findViewById(R.id.group_cardview);

        if (position == actGroup.size()+waitGroup.size()) {
            U.getInstance().myLog("position: " +position+"  groupsize : "+actGroup.size());
            //1. 안내카드 세팅
            groupCardview.setVisibility(View.GONE);
            addCardView.setVisibility(View.VISIBLE);
            addCardView.setClickable(false);
            imageView = (ImageView) convertView.findViewById(R.id.group_add_img);
            imageView.setVisibility(View.GONE);
            textView = (TextView) convertView.findViewById(R.id.group_addTxt);
            if (actGroup.size()+waitGroup.size() == E.KEY.GROUP_MAX) {
                //1-1. 그룹은 5개까지 소속될 수 있습니다.
                addCardView.setBackgroundResource(R.color.babyPink);
                textView.setTextColor(Color.WHITE);
                textView.setText(R.string.max_group);
            } else if (isManager()) {
                //1-2. 매니저 그룹이 있다면 add카드 닫기
                addCardView.setBackgroundResource(R.color.babyPink);
                textView.setText(R.string.already_manager);
            } else {
                //1-3. add카드 띄우기
                imageView.setVisibility(View.VISIBLE);
                addCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        U.getInstance().goNext(v.getContext(), GroupAddActivity.class, false, false);
                    }
                });
            }
        } else {
            //2. 그룹카드 세팅
            groupCardview.setVisibility(View.VISIBLE);
            addCardView.setVisibility(View.GONE);
            textView = (TextView) convertView.findViewById(R.id.group_name_card);
            imageView = (ImageView) convertView.findViewById(R.id.group_cardImg);
            textView2 = (TextView) convertView.findViewById(R.id.group_term);

            if (position < actGroup.size()) {
                //2-1. 활성화그룹
                if(actGroup.get(position).getManager()==1){
                    ImageView managerImg = (ImageView) convertView.findViewById(R.id.manager);
                    managerImg.setVisibility(View.VISIBLE);
                }
                U.getInstance().myLog(actGroup.get(position).toString());
                textView.setText(actGroup.get(position).getGroupTitle());
                Picasso.with(context)
                        .load(actGroup.get(position).getGroupPicUrl())
                        .fit()
                        .centerCrop()
                        .into(imageView);
                textView2.setText(String.valueOf(actGroup.get(position).getExPeriod()));
                groupCardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), GroupHomeActivity.class);
                        intent.putExtra("groupData", actGroup.get(position));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(intent);
                    }
                });
            } else if(position >= actGroup.size()&& position<actGroup.size()+waitGroup.size()){

                //2-2. 활동대기 그룹
                int mPosition = position - actGroup.size();
                U.getInstance().myLog(waitGroup.get(mPosition).toString());

                FrameLayout waitingLayout = (FrameLayout) convertView.findViewById(R.id.waiting_layout);
                waitingLayout.setVisibility(View.VISIBLE);
                waitingLayout.bringToFront();
                Button waitingLockBtn = (Button) convertView.findViewById(R.id.waiting_lock);
                waitingLockBtn.setVisibility(View.VISIBLE);

                Picasso.with(context)
                        .load(waitGroup.get(mPosition).getGroupPicUrl())
                        .fit()
                        .centerCrop()
                        .into(imageView);
//
//                Uri picUrl = Uri.parse(waitGroup.get(mPosition).getGroupPicUrl());
//                imageView.setImageURI(picUrl);

                textView.setText(waitGroup.get(mPosition).getGroupTitle());
//                textView.setTextColor(Color.WHITE);
                textView2.setVisibility(View.GONE);
                TextView textView3 = (TextView) convertView.findViewById(R.id.d_day);
                textView3.setVisibility(View.GONE);
                waitingLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(v.getContext(), GroupWaitingActivity.class);
                        intent.putExtra("groupId", waitGroup.get(mPosition).getGroup_id());
                        intent.putExtra("title", waitGroup.get(mPosition).getGroupTitle());
                        intent.putExtra("nowNum", waitGroup.get(mPosition).getNowNum());
                        intent.putExtra("maxNum", waitGroup.get(mPosition).getMaxNum());
                        intent.putExtra("date", waitGroup.get(mPosition).getGroupCreateDate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(intent);
                    }
                });

            }
             else {
                //2-3. 활동종료그룹
                FrameLayout waitingLayout = (FrameLayout) convertView.findViewById(R.id.waiting_layout);
                waitingLayout.bringToFront();
                waitingLayout.setVisibility(View.VISIBLE);
                Button waitingLockBtn = (Button) convertView.findViewById(R.id.waiting_lock);
                waitingLockBtn.setBackgroundResource(R.drawable.close_white);
                waitingLockBtn.setVisibility(View.VISIBLE);

                int mPosition = position - (actGroup.size()+waitGroup.size() + 1);
                textView.setText(unActGroup.get(mPosition).getGroupTitle());
                textView.setTextColor(Color.WHITE);
                Uri picUrl = Uri.parse(unActGroup.get(mPosition).getGroupPicUrl());
                imageView.setImageURI(picUrl);
                TextView textView3 = (TextView) convertView.findViewById(R.id.d_day);
                textView3.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                groupCardview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), GroupHomeActivity.class);
                        intent.putExtra("groupData", unActGroup.get(mPosition));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        v.getContext().startActivity(intent);
                    }
                });
            }


        }
        return convertView;
    }


    private boolean isManager() {
        boolean isManager = false;
        for (int i = 0; i < actGroup.size(); ++i) {
            if (actGroup.get(i).getManager() == 1) isManager = true;
        }
        return isManager;
    }


}