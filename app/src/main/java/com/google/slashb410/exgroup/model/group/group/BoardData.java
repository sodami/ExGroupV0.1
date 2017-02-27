package com.google.slashb410.exgroup.model.group.group;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-24.
 */

public class BoardData implements Parcelable{

    private int group_id;
    private int board_id;
    private int user_id;
    private String username;
    private String nickname;
    private String userPicUrl;
    private int categoryNum;
    private String summary;
    private String content;
    private String boardPicUrl;
    private String writeDate;
    private ArrayList<CommentData> comment = new ArrayList<>();
    private int favoriteBool;
    private int commentNum;
    private int favoriteNum;

    protected BoardData(Parcel in) {
        group_id = in.readInt();
        board_id = in.readInt();
        user_id = in.readInt();
        username = in.readString();
        nickname = in.readString();
        userPicUrl = in.readString();
        categoryNum = in.readInt();
        summary = in.readString();
        content = in.readString();
        boardPicUrl = in.readString();
        writeDate = in.readString();
        favoriteBool = in.readInt();
        commentNum = in.readInt();
        favoriteNum = in.readInt();
    }

    public static final Creator<BoardData> CREATOR = new Creator<BoardData>() {
        @Override
        public BoardData createFromParcel(Parcel in) {
            return new BoardData(in);
        }

        @Override
        public BoardData[] newArray(int size) {
            return new BoardData[size];
        }
    };

    public int getFavoriteBool() {
        return favoriteBool;
    }

    public void setFavoriteBool(int favoriteBool) {
        this.favoriteBool = favoriteBool;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(int favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserPicUrl() {
        return userPicUrl;
    }

    public void setUserPicUrl(String userPicUrl) {
        this.userPicUrl = userPicUrl;
    }

    public int getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(int categoryNum) {
        this.categoryNum = categoryNum;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBoardPicUrl() {
        return boardPicUrl;
    }

    public void setBoardPicUrl(String boardPicUrl) {
        this.boardPicUrl = boardPicUrl;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public ArrayList<CommentData> getComment() {
        return comment;
    }

    public void setComment(ArrayList<CommentData> comment) {
        this.comment = comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(group_id);
        dest.writeInt(board_id);
        dest.writeInt(user_id);
        dest.writeString(username);
        dest.writeString(nickname);
        dest.writeString(userPicUrl);
        dest.writeInt(categoryNum);
        dest.writeString(summary);
        dest.writeString(content);
        dest.writeString(boardPicUrl);
        dest.writeString(writeDate);
        dest.writeTypedList(comment);
        dest.writeInt(favoriteBool);
        dest.writeInt(commentNum);
        dest.writeInt(favoriteNum);
    }
}
