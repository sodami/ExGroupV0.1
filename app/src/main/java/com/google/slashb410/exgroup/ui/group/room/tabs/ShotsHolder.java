package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.model.group.group.BoardData;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.ui.group.room.tabs.comments.GroupShotsCommentsActivity;
import com.google.slashb410.exgroup.util.U;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    BoardData mDatas;
    int boardId;


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

    public void bindOnCard(Context context, BoardData datas) {

        mDatas = datas;
        boardId = mDatas.getBoard_id();

        switch (mDatas.getCategoryNum()) {
            case 0:
                menuImg.setImageResource(R.drawable.scale_white);
                todayWeight.setVisibility(View.VISIBLE);
                break;
            case 1:
                menuImg.setImageResource(R.drawable.exercise_white);
                break;
            case 2:
                menuImg.setImageResource(R.drawable.meal_white);
                break;
        }


        //holder.profileImg.setImageDrawable(mDatas.getPic());
        nickname.setText(mDatas.getNickname());

        String customDateNTime = customDateNTime(mDatas.getWriteDate());

        dateNtime.setText(customDateNTime);
        summary.setText(mDatas.getSummary());
        content.setText(mDatas.getContent());
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas.getFavoriteBool() == 0) {
                    callOnLike();
                } else {
                    callOffLike();
                }
            }
        });
        numLike.setText(mDatas.getFavoriteNum() + "");
        numComments.setText(mDatas.getCommentNum() + "");
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
                                int idx = mDatas.getBoard_id();

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
                U.getInstance().myLog("코맨트 온클릭");
                Intent intent = new Intent(context, GroupShotsCommentsActivity.class);
                intent.putExtra("boardData", mDatas);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(intent);

            }
        });

    }

    private void callOffLike() {
        Call<ResStandard> resUnFavorite = NetSSL.getInstance().getGroupImpFactory().unFavorite(String.valueOf(boardId));
        resUnFavorite.enqueue(new Callback<ResStandard>() {
            @Override
            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                if(response.body()==null) {
                    U.getInstance().myLog("ResUnFavorite body is null");
                    return;
                }
                likeBtn.setImageResource(R.drawable.like_gray);
            }

            @Override
            public void onFailure(Call<ResStandard> call, Throwable t) {
                U.getInstance().myLog("ResUnFavorite 접근실패 : "+t.toString());
            }
        });
    }

    private void callOnLike() {
        Call<ResStandard> resFavorite = NetSSL.getInstance().getGroupImpFactory().favorite(String.valueOf(boardId));
        resFavorite.enqueue(new Callback<ResStandard>() {
            @Override
            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                if(response.body()!=null) return;
                U.getInstance().myLog(response.body().getResult());
                likeBtn.setImageResource(R.drawable.like_pink);
            }

            @Override
            public void onFailure(Call<ResStandard> call, Throwable t) {
                U.getInstance().myLog("callOnLike 접근실패 : "+t.toString());
            }
        });
    }

    private String customDateNTime(String writeDate) {

        String mWriteDate ="";

        String year = String.valueOf(writeDate.indexOf(0,3));


        return mWriteDate;
    }


}
