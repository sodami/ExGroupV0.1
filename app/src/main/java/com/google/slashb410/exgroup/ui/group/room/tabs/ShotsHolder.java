package com.google.slashb410.exgroup.ui.group.room.tabs;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.E;
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

    Context context;

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
        this.context = context;
        boardId = mDatas.getBoard_id();

        if(datas.getUser_id()== E.KEY.USER_ID){
            delete.setVisibility(View.VISIBLE);

        }

        if(datas.getFavoriteBool()==1){
            likeBtn.setBackgroundResource(R.drawable.like_pink);
        }

        U.getInstance().myLog("BoardData : "+datas.toString());


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

        String customDate = U.getInstance().customDateNTime(mDatas.getWriteDate());

        dateNtime.setText(customDate);
        summary.setText(mDatas.getSummary());
        content.setText(mDatas.getContent());
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onLike(v);
            }
        });
        numLike.setText(mDatas.getFavoriteNum() + "");
        numLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLike(v);
            }
        });

        numComments.setText(mDatas.getCommentNum() + "");
        numComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onComments(v);
            }
        });

        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onComments(v);
            }
        });

    }

    private void onComments(View view) {

        U.getInstance().myLog("코맨트 온클릭");
        Intent intent = new Intent(context, GroupShotsCommentsActivity.class);
        intent.putExtra("boardData", mDatas);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);

    }

    private void onLike(View view) {
        if (mDatas.getFavoriteBool() == 0) {
            callOnLike();
        } else {
            callOffLike();
        }
    }

    private void callOffLike() {
        Call<ResStandard> resUnFavorite = NetSSL.getInstance().getGroupImpFactory().unFavorite(boardId);
        resUnFavorite.enqueue(new Callback<ResStandard>() {
            @Override
            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        likeBtn.setBackgroundResource(R.drawable.like_gray);
                        numLike.setText(mDatas.getFavoriteNum()-1+"");
                        mDatas.setFavoriteNum(mDatas.getFavoriteNum()-1);
                        mDatas.setFavoriteBool(0);

                    }else{
                        U.getInstance().myLog("ResUnFavorite body is null" + response.message());
                        return;
                    }

                }else{
                    U.getInstance().myLog("resOffLike is not Successful : "+response.message());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResStandard> call, Throwable t) {
                U.getInstance().myLog("ResUnFavorite 접근실패 : "+t.toString());
            }
        });
    }

    private void callOnLike() {
        Call<ResStandard> resFavorite = NetSSL.getInstance().getGroupImpFactory().favorite(boardId);
        resFavorite.enqueue(new Callback<ResStandard>() {
            @Override
            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        likeBtn.setBackgroundResource(R.drawable.like_pink);
                        numLike.setText(mDatas.getFavoriteNum()+1+"");
                        mDatas.setFavoriteNum(mDatas.getFavoriteNum()+1);
                        mDatas.setFavoriteBool(1);

                    }else{
                        U.getInstance().myLog("ResFavorite body is null" + response.message());
                        return;
                    }

                }else{
                    U.getInstance().myLog("resOnLike is not Successful : "+response.message());
                    return;
                }

            }

            @Override
            public void onFailure(Call<ResStandard> call, Throwable t) {
                U.getInstance().myLog("callOnLike 접근실패 : "+t.toString());
            }
        });
    }



}
