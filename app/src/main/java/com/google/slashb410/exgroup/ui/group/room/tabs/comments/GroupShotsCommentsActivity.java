package com.google.slashb410.exgroup.ui.group.room.tabs.comments;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.model.group.InnerCommentData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupShotsCommentsActivity extends AppCompatActivity {

    @BindView(R.id.comments_list)
    ListView commentList;
    CommentsListAdapter adapter;
    ArrayList<InnerCommentData> commentDatas;
    InnerCommentData commentData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_shots_comments);

        ButterKnife.bind(this);

        commentData = new InnerCommentData("슬비", "ㅇㅁㄴㅇㄹ", "2017년 2월 18일 18시 21분", "아무글이나 아무아무");

        commentDatas = new ArrayList<>();
        commentDatas.add(commentData);

        commentList = new ListView(this);
        adapter = new CommentsListAdapter(this, commentDatas);

        commentList.setAdapter(adapter);



    }
}
