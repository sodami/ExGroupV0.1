package com.google.slashb410.exgroup.model.group.group;

/**
 * Created by Tacademy on 2017-02-22.
 */

public class GroupSearchData {
    private int id;
    private int nowNum;
    private int maxNum;
    private String groupTitle;
    private String groupPicUrl;
    private int exPeriod;
    private String startDate;
    private String goalDate;
    private String groupCreateDate;
    private int activation;
    private String ctime;
    private String utime;
    private int btnAppear;

    public GroupSearchData(int id, int nowNum, int maxNum, String groupTitle, String groupPicUrl, int exPeriod, String startDate, String goalDate, String groupCreateDate, int activation, String ctime, String utime, int btnAppear) {
        this.id = id;
        this.nowNum = nowNum;
        this.maxNum = maxNum;
        this.groupTitle = groupTitle;
        this.groupPicUrl = groupPicUrl;
        this.exPeriod = exPeriod;
        this.startDate = startDate;
        this.goalDate = goalDate;
        this.groupCreateDate = groupCreateDate;
        this.activation = activation;
        this.ctime = ctime;
        this.utime = utime;
        this.btnAppear = btnAppear;
    }

    @Override
    public String toString() {
        return "GroupSearchData{" +
                "id=" + id +
                ", nowNum=" + nowNum +
                ", maxNum=" + maxNum +
                ", groupTitle='" + groupTitle + '\'' +
                ", groupPicUrl='" + groupPicUrl + '\'' +
                ", exPeriod=" + exPeriod +
                ", startDate='" + startDate + '\'' +
                ", goalDate='" + goalDate + '\'' +
                ", groupCreateDate='" + groupCreateDate + '\'' +
                ", activation=" + activation +
                ", ctime='" + ctime + '\'' +
                ", utime='" + utime + '\'' +
                ", btnAppear=" + btnAppear +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNowNum() {
        return nowNum;
    }

    public void setNowNum(int nowNum) {
        this.nowNum = nowNum;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getGroupPicUrl() {
        return groupPicUrl;
    }

    public void setGroupPicUrl(String groupPicUrl) {
        this.groupPicUrl = groupPicUrl;
    }

    public int getExPeriod() {
        return exPeriod;
    }

    public void setExPeriod(int exPeriod) {
        this.exPeriod = exPeriod;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getGoalDate() {
        return goalDate;
    }

    public void setGoalDate(String goalDate) {
        this.goalDate = goalDate;
    }

    public String getGroupCreateDate() {
        return groupCreateDate;
    }

    public void setGroupCreateDate(String groupCreateDate) {
        this.groupCreateDate = groupCreateDate;
    }

    public int getActivation() {
        return activation;
    }

    public void setActivation(int activation) {
        this.activation = activation;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public int getBtnAppear() {
        return btnAppear;
    }

    public void setBtnAppear(int btnAppear) {
        this.btnAppear = btnAppear;
    }
}

