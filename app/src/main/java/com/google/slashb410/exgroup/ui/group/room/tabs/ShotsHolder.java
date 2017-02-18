package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.ShotData;
import com.google.slashb410.exgroup.ui.group.room.tabs.comments.GroupShotsCommentsActivity;
import com.google.slashb410.exgroup.util.U;

/**
 * Created by Tacademy on 2017-02-03.
 */
class ShotsHolder extends RecyclerView.ViewHolder {

    ImageView menuImg;
    ImageView delete;
    TextView nickname;
    TextView dateNtime;
    ImageView profileImg;
    ImageView shot;
    TextView summary;
    TextView content;
    TextView numLike;
    TextView numComments;
        TextView todayWeight;
    ImageButton likeBtn;
    ImageButton commentBtn;


    public ShotsHolder(View itemView) {
        super(itemView);
        menuImg = (ImageView) itemView.findViewById(R.id.menuImg_shot);
        delete = (ImageView) itemView.findViewById(R.id.delete_shot);
        nickname = (TextView) itemView.findViewById(R.id.nickname_shot);
        dateNtime = (TextView) itemView.findViewById(R.id.dateNtime_shot);
        profileImg = (ImageView) itemView.findViewById(R.id.profileImg_shot);
        shot = (ImageView) itemView.findViewById(R.id.shotImg);
        summary = (TextView) itemView.findViewById(R.id.summary);
        content = (TextView) itemView.findViewById(R.id.content_shot);
        likeBtn = (ImageButton) itemView.findViewById(R.id.likeBtn);
        numLike = (TextView) itemView.findViewById(R.id.numLike);
        numComments = (TextView) itemView.findViewById(R.id.numComments);

        commentBtn = (ImageButton) itemView.findViewById(R.id.commentImgBtn);
        todayWeight = (TextView) itemView.findViewById(R.id.weightTxt);
    }

    public void bindOnCard(Context context, ShotData results) {

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
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!results.isLike()){
                    likeBtn.setImageResource(R.drawable.like_pink);
                    //db 변경 islike->true
                }else{
                    likeBtn.setImageResource(R.drawable.like_gray);
                    //db 변경 islike->false
                }
            }
        });
        numLike.setText(mResults.getNumLike()+"");
        numComments.setText(mResults.getNumComment()+"");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("정말로 삭제하시겠습니까?")
                        .setMessage("삭제된 내용과 댓글을 복구할 수 없습니다.\n추가된 활동지수 역시 취소됩니다.")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //해당 내용 삭제
                                int idx = mResults.getIdx();

                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();


            }
        });

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                U.getInstance().goNext(context, GroupShotsCommentsActivity.class, false, false);
            }
        });

    }


}
