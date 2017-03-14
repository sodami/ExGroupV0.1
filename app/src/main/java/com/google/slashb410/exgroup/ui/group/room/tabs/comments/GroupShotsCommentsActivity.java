package com.google.slashb410.exgroup.ui.group.room.tabs.comments;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.slashb410.exgroup.R;
import com.google.slashb410.exgroup.db.E;
import com.google.slashb410.exgroup.model.group.ResStandard;
import com.google.slashb410.exgroup.model.group.group.BoardData;
import com.google.slashb410.exgroup.model.group.group.CommentData;
import com.google.slashb410.exgroup.model.group.group.ReqComment;
import com.google.slashb410.exgroup.net.NetSSL;
import com.google.slashb410.exgroup.util.U;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.slashb410.exgroup.R.id.comment_inputBtn;
import static com.google.slashb410.exgroup.R.id.numLike;

public class GroupShotsCommentsActivity extends AppCompatActivity {

    @BindView(R.id.comments_list)
    ListView commentList;
    ArrayList<CommentData> commentDatas;
    CommentsListAdapter adapter;


    @BindView(R.id.nick_commentEdit)
    TextView nickComment;
    @BindView(R.id.commentEdit)
    EditText commentEdit;
    @BindView(R.id.profile_comment_write)
    CircleImageView profile_write;

    @BindView(R.id.menuImg_shot)
    ImageView menuImg;
    @BindView(R.id.profileImg_shot)
    ImageView profile;
    @BindView(R.id.nickname_shot)
    TextView nick;
    @BindView(R.id.dateNtime_shot)
    TextView dateNtime;
    @BindView(R.id.shotImg)
    ImageView shotImg;
    @BindView(R.id.summary)
    TextView summary;
    @BindView(R.id.content_shot)
    TextView content;
    @BindView(R.id.likeBtn)
    ImageButton likeBtn;
    @BindView(R.id.weightTxt)
    TextView todayWeight;
    @BindView(R.id.numLike)
    TextView numLike;
    @BindView(R.id.numComments)
    TextView numComments;
    @BindView(R.id.commentImgBtn)
    ImageButton commentImg;

    @BindView(R.id.delete_shot)
    ImageButton deleteBtn;

    String nickname;
    String picUrl;
    int boardId;

    BoardData boardData;
    CommentData addComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_shots_comments);

        ButterKnife.bind(this);

        //1. 데이터 전달받기
        Intent intent = getIntent();
        boardData = intent.getParcelableExtra("boardData");

        U.getInstance().myLog("boardData : " + boardData.toString());

        nickname = E.KEY.USER_NICKNAME;
        picUrl = E.KEY.USER_PROFILE;
        boardId = boardData.getBoard_id();

        nickComment.setText(nickname);
        Picasso.with(this).load(picUrl).fit().centerCrop().into(profile_write);

        //2. 게시글 세팅
        setBoard();

        //3. 코멘트 리스트 세팅
        setComments();

    }

    private void setComments() {
        commentDatas = new ArrayList<>();

        commentDatas = boardData.getComment();
        adapter = new CommentsListAdapter(this, commentDatas);
        commentList.setAdapter(adapter);
    }

    @OnClick(R.id.comment_inputBtn)
    public void inputComment() {

        int boardId = boardData.getBoard_id();
        String content = commentEdit.getText().toString();

        ReqComment reqComment = new ReqComment(boardId, content);

        Call<ResStandard> resComment = NetSSL.getInstance().getGroupImpFactory().addComment(reqComment);
        resComment.enqueue(new Callback<ResStandard>() {
            @Override
            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().equals("댓글 등록을 성공했습니다.")) {
                            U.getInstance().myLog("댓글 등록 성공");
                            //4-1. 등록 성공 했다면 다시 코멘트리스트 재요청
//                    reCallComment(); //차후 개발
                            //단순 리스트 추가
                                } else {
                            U.getInstance().myLog("resComment Body is null : " + response.message());
                        }

                        int count = adapter.getCount();
                        String customDateNTime = U.getInstance().currentDateNTime();
                        addComment = new CommentData(0, boardId, E.KEY.USER_ID, E.KEY.USER_NAME, E.KEY.USER_NICKNAME, "", commentEdit.getText().toString(), customDateNTime);
                        boardData.getComment().add(count + 1, addComment);

                        adapter.notifyDataSetChanged();

                    }
                } else {
                    U.getInstance().myLog("resComment is NOT successful : " + response.message());
                }
            }


            @Override
            public void onFailure(Call<ResStandard> call, Throwable t) {
                U.getInstance().myLog("resComment 접근실패 : " + t.toString());
            }
        });

    }


    public void setBoard() {

        commentImg.setBackgroundResource(R.drawable.comments_pink);
        deleteBtn.setBackgroundResource(R.drawable.cancel_white);
        numComments.setTextColor(getResources().getColor(R.color.babyPink));

        switch (boardData.getCategoryNum()) {
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


        //holder.profileImg.setImageDrawable(boardData.getPic());
        nick.setText(boardData.getNickname());

        String customDateNTime = U.getInstance().customDateNTime(boardData.getWriteDate());

        dateNtime.setText(customDateNTime);
        summary.setText(boardData.getSummary());
        content.setText(boardData.getContent());
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boardData.getFavoriteBool() == 0) {
                    callOnLike();
                } else {
                    callOffLike();
                }
            }
        });
        numLike.setText(boardData.getFavoriteNum() + "");
        numComments.setText(boardData.getCommentNum() + "");

    }
//
//    private void reCallComment() {
//
//        Call<ResBoardList>
//    }


    private void callOffLike() {
        Call<ResStandard> resUnFavorite = NetSSL.getInstance().getGroupImpFactory().unFavorite(boardId);
        resUnFavorite.enqueue(new Callback<ResStandard>() {
            @Override
            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        likeBtn.setBackgroundResource(R.drawable.like_gray);
                        numLike.setText(boardData.getFavoriteNum()-1);
                        boardData.setFavoriteNum(boardData.getFavoriteNum()-1);
                        boardData.setFavoriteBool(0);

                    } else {
                        U.getInstance().myLog("ResUnFavorite body is null" + response.message());
                        return;
                    }

                } else {
                    U.getInstance().myLog("resOffLike is not Successful : " + response.message());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResStandard> call, Throwable t) {
                U.getInstance().myLog("ResUnFavorite 접근실패 : " + t.toString());
            }
        });
    }

    private void callOnLike() {
        Call<ResStandard> resFavorite = NetSSL.getInstance().getGroupImpFactory().favorite(boardId);
        resFavorite.enqueue(new Callback<ResStandard>() {
            @Override
            public void onResponse(Call<ResStandard> call, Response<ResStandard> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        likeBtn.setBackgroundResource(R.drawable.like_pink);
                        numLike.setText(boardData.getFavoriteNum()+1);
                        boardData.setFavoriteNum(boardData.getFavoriteNum()+1);
                        boardData.setFavoriteBool(1);


                    } else {
                        U.getInstance().myLog("ResFavorite body is null" + response.message());
                        return;
                    }

                } else {
                    U.getInstance().myLog("resOnLike is not Successful : " + response.message());
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResStandard> call, Throwable t) {
                U.getInstance().myLog("callOnLike 접근실패 : " + t.toString());
            }
        });
    }
}
