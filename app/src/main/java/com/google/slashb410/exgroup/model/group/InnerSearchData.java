package com.google.slashb410.exgroup.model.group;

/**
 * Created by Tacademy on 2017-02-16.
 */


public class InnerSearchData{

    public InnerSearchData() {
    }

    private int groupId;
    private String groupTitle;
    private String startDate;
    private String picUrl;
    private int maxNum;
    private int countNum;

    public InnerSearchData(int groupId, String groupTitle, String startDate, String picUrl, int maxNum, int countNum) {
        this.groupId = groupId;
        this.groupTitle = groupTitle;
        this.startDate = startDate;
        this.picUrl = picUrl;
        this.maxNum = maxNum;
        this.countNum = countNum;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }
}

