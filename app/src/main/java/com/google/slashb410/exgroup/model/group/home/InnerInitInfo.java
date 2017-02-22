package com.google.slashb410.exgroup.model.group.home;

/**
 * Created by Tacademy on 2017-02-21.
 */

public class InnerInitInfo {
    int id;
    String nickname;
    String picUrl;
    String weight;
    String height;
    int BMI;
    int age;
    int seqAttendNum;
    int activation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getBMI() {
        return BMI;
    }

    public void setBMI(int BMI) {
        this.BMI = BMI;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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
