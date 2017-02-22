package com.google.slashb410.exgroup.model.group.home;

/**
 * Created by Tacademy on 2017-02-20.
 */

public class InnerUserData {

    String id;
    String nickname;
    String picUrl;
    String wight;
    String height;
    String BMI;
    String age;
    int seqAttendNum;
    int activation;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getWight() {
        return wight;
    }

    public void setWight(String wight) {
        this.wight = wight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getSeqAttendNum() {
        return seqAttendNum;
    }

    public void setSeqAttendNum(int seqAttendNum) {
        this.seqAttendNum = seqAttendNum;
    }

    public int getActivation() {
        return activation;
    }

    public void setActivation(int activation) {
        this.activation = activation;
    }
}
