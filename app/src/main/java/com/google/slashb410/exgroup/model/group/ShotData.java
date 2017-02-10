package com.google.slashb410.exgroup.model.group;

/**
 * Created by Tacademy on 2017-02-05.
 */

public class ShotData {

    int boardType;
    String profile;
    String nickname;
    String dateNtime;
    String summary;
    String content;
    String pic;
    int numLike;
    int numComment;

    public ShotData() {
    }

    public void deleteShotData(){
        this.boardType = 0;
        this.profile = null;
        this.nickname = null;
        this.dateNtime = null;
        this.summary = null;
        this.content = null;
        this.pic = null;
        this.numLike = 0;
        this.numComment = 0;
    }

    public ShotData(int boardType, String profile, String nickname, String dateNtime, String summary, String content, String pic, int numLike, int numComment) {
        this.boardType = boardType;
        this.profile = profile;
        this.nickname = nickname;
        this.dateNtime = dateNtime;
        this.summary = summary;
        this.content = content;
        this.pic = pic;
        this.numLike = numLike;
        this.numComment = numComment;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getBoardType() {
        return boardType;
    }

    public void setBoardType(int boardType) {
        this.boardType = boardType;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDateNtime() {
        return dateNtime;
    }

    public void setDateNtime(String dateNtime) {
        this.dateNtime = dateNtime;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getNumLike() {
        return numLike;
    }

    public void setNumLike(int numLike) {
        this.numLike = numLike;
    }

    public int getNumComment() {
        return numComment;
    }

    public void setNumComment(int numComment) {
        this.numComment = numComment;
    }
}