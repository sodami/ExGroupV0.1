package com.google.slashb410.exgroup.model.group;

/**
 * Created by Tacademy on 2017-02-07.
 */

public class CalendarListData {
    private int boardType;
    private String profileURL;
    private String nickname;
    private String dateNtime;
    private String summary;

    public CalendarListData(int boardType, String profileURL, String nickname, String dateNtime, String summary) {
        this.boardType = boardType;
        this.profileURL = profileURL;
        this.nickname = nickname;
        this.dateNtime = dateNtime;
        this.summary = summary;
    }

    public int getBoardType() {
        return boardType;
    }

    public void setBoardType(int boardType) {
        this.boardType = boardType;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
}
