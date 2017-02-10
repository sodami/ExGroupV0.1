package com.google.slashb410.exgroup.model.group;

/**
 * Created by Tacademy on 2017-02-09.
 */

public class WriteData {

    int boardType;
    String nickName;
    String dateNTime;
    String summary;
    String content;
    String pic;

    public WriteData() {

    }


    public void deleteWriteData(){
        this.boardType = 0;
        this.nickName = null;
        this.dateNTime = null;
        this.summary = null;
        this.content = null;
        this.pic = null;
    }

    public WriteData(int boardType, String nickName, String dateNTime, String summary, String content, String pic) {
        this.boardType = boardType;
        this.nickName = nickName;
        this.dateNTime = dateNTime;
        this.summary = summary;
        this.content = content;
        this.pic = pic;
    }

    public int getBoardType() {
        return boardType;
    }

    public void setBoardType(int boardType) {
        this.boardType = boardType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDateNTime() {
        return dateNTime;
    }

    public void setDateNTime(String dateNTime) {
        this.dateNTime = dateNTime;
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
}

