package com.google.slashb410.exgroup.model.group.home;

/**
 * Created by Tacademy on 2017-02-21.
 */

public class ResMe {
    private int id;
    private String username;
    private String weight;
    private String height;
    private String age;
    private String BMI;
    private int seqAttendNum;
    private String nickname;
    private String picUrl;
    private int point;
    private int activation;


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

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getActivation() {
        return activation;
    }

    public void setActivation(int activation) {
        this.activation = activation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBMI() {
        return BMI;
    }

    public void setBMI(String BMI) {
        this.BMI = BMI;
    }

    public int getSeqAttendNum() {
        return seqAttendNum;
    }

    public void setSeqAttendNum(int seqAttendNum) {
        this.seqAttendNum = seqAttendNum;
    }
}
