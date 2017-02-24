package com.google.slashb410.exgroup.model.group.group;

import java.io.Serializable;

/**
 * Created by Tacademy on 2017-02-21.
 */

public class GroupData implements Serializable{
    private int id;
    private int user_id;
    private int group_id;
    private int manager;
    private int waitActRst;
    private String ctime;
    private String utime;
    private String weight;
    private String weightPicUrl;
    private int nowNum;
    private int maxNum;
    private String groupTitle;
    private String groupPicUrl;
    private int exPeriod;
    private String startDate;
    private String goalDate;
    private String groupCreateDate;
    private int activation;

    @Override
    public String toString() {
        return "GroupData{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", group_id=" + group_id +
                ", manager=" + manager +
                ", waitActRst=" + waitActRst +
                ", ctime='" + ctime + '\'' +
                ", utime='" + utime + '\'' +
                ", weight='" + weight + '\'' +
                ", weightPicUrl='" + weightPicUrl + '\'' +
                ", nowNum=" + nowNum +
                ", maxNum=" + maxNum +
                ", groupTitle='" + groupTitle + '\'' +
                ", groupPicUrl='" + groupPicUrl + '\'' +
                ", exPeriod=" + exPeriod +
                ", startDate='" + startDate + '\'' +
                ", goalDate='" + goalDate + '\'' +
                ", groupCreateDate='" + groupCreateDate + '\'' +
                ", activation=" + activation +
                '}';
    }

    public int getWaitActRst() {
        return waitActRst;
    }

    public void setWaitActRst(int waitActRst) {
        this.waitActRst = waitActRst;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeightPicUrl() {
        return weightPicUrl;
    }

    public void setWeightPicUrl(String weightPicUrl) {
        this.weightPicUrl = weightPicUrl;
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
}