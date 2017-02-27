package com.google.slashb410.exgroup.model.group.group;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tacademy on 2017-02-24.
 */

public class CommentData implements Parcelable{

    private int comment_id;
    private int board_id;
    private int user_id;
    private String username;
    private String nickname;
    private String userPicUrl;
    private String content;
    private String writeDate;

    public CommentData(int comment_id, int board_id, int user_id, String username, String nickname, String userPicUrl, String content, String writeDate) {
        this.comment_id = comment_id;
        this.board_id = board_id;
        this.user_id = user_id;
        this.username = username;
        this.nickname = nickname;
        this.userPicUrl = userPicUrl;
        this.content = content;
        this.writeDate = writeDate;
    }

    protected CommentData(Parcel in) {
        comment_id = in.readInt();
        board_id = in.readInt();
        user_id = in.readInt();
        username = in.readString();
        nickname = in.readString();
        userPicUrl = in.readString();
        content = in.readString();
        writeDate = in.readString();
    }

    public static final Creator<CommentData> CREATOR = new Creator<CommentData>() {
        @Override
        public CommentData createFromParcel(Parcel in) {
            return new CommentData(in);
        }

        @Override
        public CommentData[] newArray(int size) {
            return new CommentData[size];
        }
    };

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(comment_id);
        dest.writeInt(board_id);
        dest.writeInt(user_id);
        dest.writeString(username);
        dest.writeString(nickname);
        dest.writeString(userPicUrl);
        dest.writeString(content);
        dest.writeString(writeDate);
    }
}
