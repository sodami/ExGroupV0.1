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
import com.google.slashb410.exgroup.model.group.InnerCommentData;
import com.google.slashb410.exgroup.util.U;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-06.
 */

public class CommentsListAdapter extends BaseAdapter{


    ArrayList<InnerCommentData> commentDatas;
    LayoutInflater inflater;
    CommentHolder commentHolder;

    public CommentsListAdapter(Context context, ArrayList<InnerCommentData> commentDatas) {
        this.commentDatas = commentDatas;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        U.getInstance().myLog(commentDatas.size()+"");
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

        Picasso.with(view.getContext())
                .load(commentDatas.get(position).getPicUrl())
                .fit().centerCrop()
                .into(commentHolder.profile);
        commentHolder.nickname.setText(commentDatas.get(position).getNickname());
        commentHolder.dateNTime.setText(commentDatas.get(position).getDate());
        commentHolder.content.setText(commentDatas.get(position).getContent());

        //user id와 작성자가 같다면 delete 띄우기


        return view;
    }


    class CommentHolder{

        ImageView profile;
        TextView nickname;
        TextView dateNTime;
        TextView content;
        ImageButton delete;

    }
}
