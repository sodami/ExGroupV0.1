package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.ShotData;

/**
 * Created by Tacademy on 2017-02-03.
 */
class ShotsHolder extends RecyclerView.ViewHolder {

    ImageView menuImg;
    TextView nickname;
    TextView dateNtime;
    ImageView profileImg;
    ImageView shot;
    TextView summary;
    TextView content;
    TextView numLike;
    TextView numComments;

    TextView todayWeight;


    public ShotsHolder(View itemView) {
        super(itemView);
        menuImg = (ImageView) itemView.findViewById(R.id.menuImg_shot);
        nickname = (TextView) itemView.findViewById(R.id.nickname_shot);
        dateNtime = (TextView) itemView.findViewById(R.id.dateNtime_shot);
        profileImg = (ImageView) itemView.findViewById(R.id.profileImg_shot);
        shot = (ImageView) itemView.findViewById(R.id.shotImg);
        summary = (TextView) itemView.findViewById(R.id.summary);
        content = (TextView) itemView.findViewById(R.id.content_shot);
        numLike = (TextView) itemView.findViewById(R.id.numLike);
        numComments = (TextView) itemView.findViewById(R.id.numComments);

        todayWeight = (TextView) itemView.findViewById(R.id.weightTxt);
    }

    public void bindOnCard(ShotData results) {

        ShotData mResults = results;
        switch (mResults.getBoardType()+"") {
            case "0":
                menuImg.setImageResource(R.drawable.scale_white);
                todayWeight.setVisibility(View.VISIBLE);
                break;
            case "1":
                menuImg.setImageResource(R.drawable.exercise_white);
                break;
            case "2":
                menuImg.setImageResource(R.drawable.meal_white);
                break;
        }

        //holder.profileImg.setImageDrawable(mResults.getPic());
        nickname.setText(mResults.getNickname());
        dateNtime.setText(mResults.getDateNtime());
        summary.setText(mResults.getSummary());
        content.setText(mResults.getContent());
        numLike.setText(mResults.getNumLike()+"");
        numComments.setText(mResults.getNumComment()+"");


    }


}
