package com.google.slashb410.exgroup.model.group;

/**
 * Created by drizzle on 2017-02-18.
 */
public class InnerCommentData {
    private String nickname;
    private String picUrl;
    private String date;
    private String content;

    public InnerCommentData(String nickname, String picUrl, String date, String content) {
        this.nickname = nickname;
        this.picUrl = picUrl;
        this.date = date;
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
