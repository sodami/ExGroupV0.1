package com.google.slashb410.exgroup.ui.group.room.tabs.comments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.model.group.group.BoardData;
import com.google.slashb410.exgroup.model.group.group.CommentData;
import com.google.slashb410.exgroup.model.group.group.ReqComment;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupShotsCommentsActivity extends AppCompatActivity {

    @BindView(R.id.comments_list)
    ListView commentList;
    CommentsListAdapter adapter;

    @BindView(R.id.delete_shot)
    ImageButton deleteBtn;

    @BindView(R.id.commentEdit)
    EditText commentEdit;

    BoardData boardData;
    CommentData addComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_shots_comments);

        ButterKnife.bind(this);

        //1. 데이터 전달받기
        deleteBtn.setBackgroundResource(R.drawable.cancel_white);
        Intent intent = getIntent();
        boardData = intent.getParcelableExtra("boardData");

        //2. 게시글 세팅
        setBoard();

        //3. 코멘트 리스트 세팅
        U.getInstance().myLog("board ID : "+boardData.getBoard_id());
        adapter = new CommentsListAdapter(this, boardData.getComment());
        commentList.setAdapter(adapter);

    }

    //4. 댓글 달기
    @OnClick(R.id.comment_inputBtn)
    public void setBoard() {

        int boardId = boardData.getBoard_id();
        String content= commentEdit.getText().toString();

        ReqComment reqComment = new ReqComment(boardId, content);

        Call<String> resComment = NetSSL.getInstance().getGroupImpFactory().addComment(reqComment);
        resComment.enqueue(new Callback<String>(){
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.body().equals("댓글 등록을 성공했습니다.")) {
                    U.getInstance().myLog("댓글 등록 성공");
                    //4-1. 등록 성공 했다면 다시 코멘트리스트 재요청
//                    reCallComment(); //차후 개발
                    //단순 리스트 추가
                    int count = adapter.getCount();
                    String customDateNTime = customDateNTime();
                    addComment = new CommentData(0, boardId, E.KEY.USER_ID, E.KEY.USER_NAME, E.KEY.USER_NICKNAME, "", commentEdit.getText().toString(), customDateNTime);
                    boardData.getComment().add(count + 1, addComment);

                    adapter.notifyDataSetChanged();
                }else {
                    U.getInstance().myLog("댓글 등록 실패");
                }
            }


            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
//
//    private void reCallComment() {
//
//        Call<ResBoardList>
//    }

    public String customDateNTime(){

        String date[] = U.getInstance().currentYYmmDD();
        String time[] = U.getInstance().currentTime();

        return date[0]+"년 "+date[1]+"월 "+date[2]+"일 "+time[0]+"시 "+time[1]+"분";
    }
}
