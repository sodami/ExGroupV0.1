package com.google.slashb410.exgroup.ui.group.room.tabs.comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.model.group.group.CommentData;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tacademy on 2017-02-06.
 */

public class CommentsListAdapter extends BaseAdapter{


    ArrayList<CommentData> commentDatas;
    LayoutInflater inflater;
    CommentHolder commentHolder;
    int userId;

    public CommentsListAdapter(Context context, ArrayList<CommentData> commentDatas) {
        this.commentDatas = commentDatas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        U.getInstance().myLog("코멘트 count : "+commentDatas.size()+"");
        return commentDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return commentDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        U.getInstance().myLog("겟뷰 들어오긴 함");
        View view = convertView;

        view = inflater.inflate(R.layout.cell_comments_list, parent, false);
        if(view==null){
            commentHolder.profile = (ImageView) view.findViewById(R.id.profile_comment);
            commentHolder.nickname = (TextView) view.findViewById(R.id.nickname_comment);
            commentHolder.dateNTime = (TextView) view.findViewById(R.id.date_comment);
            commentHolder.content = (TextView) view.findViewById(R.id.content_comment);
            commentHolder.delete = (ImageButton) view.findViewById(R.id.delete_comment);
        }

        if(commentDatas.get(position).getUserPicUrl()!=null) {
            Picasso.with(view.getContext())
                    .load(commentDatas.get(position).getUserPicUrl())
                    .fit().centerCrop()
                    .into(commentHolder.profile);
        }
        if(commentDatas.get(position).getNickname()!=null) commentHolder.nickname.setText(commentDatas.get(position).getNickname());
        if(commentDatas.get(position).getWriteDate()!=null) commentHolder.dateNTime.setText(commentDatas.get(position).getWriteDate());
        if(commentDatas.get(position).getContent()!=null) commentHolder.content.setText(commentDatas.get(position).getContent());

        //user id와 작성자가 같다면 delete 띄우기
        userId = E.KEY.USER_ID;
        if(commentDatas.get(position).getUser_id()==userId){
            commentHolder.delete.setVisibility(View.VISIBLE);
            commentHolder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callCommentDelete(position);
                }


            });
        }

        return view;
    }

    public void callCommentDelete(int position){
        Call<ResStandard> resCommentDelete = NetSSL.getInstance().getGroupImpFactory().deleteComment(commentDatas.get(position).getComment_id());

        resCommentDelete.enqueue(new Callback<ResStandard>() {
            @Override
            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                if(response.isSuccessful()){
                    if(response.body() !=null){
                        U.getInstance().myLog("댓글 삭제 성공");
                    }else{
                        U.getInstance().myLog("resDeleteComment Body is null : "+response.message());
                    }
                }else{
                    U.getInstance().myLog("resDeleteComment is NOT successful : "+response.message());
                }
            }

            @Override
            public void onFailure(Call<ResStandard> call, Throwable t) {

            }
        });
    }

    class CommentHolder{

        ImageView profile;
        TextView nickname;
        TextView dateNTime;
        TextView content;
        ImageButton delete;

    }
}
