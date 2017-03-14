package com.google.slashb410.exgroup.model.group.group;

/**
 * Created by drizzle on 2017-03-13.
 */

public class RankUser{
    private String nickname;
    private int rank;
    private String picUrl;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}