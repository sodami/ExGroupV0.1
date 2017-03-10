package com.google.slashb410.exgroup.model.group;

import java.io.Serializable;

/**
 * Created by drizzle on 2017-03-10.
 */

public class MyData implements Serializable {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String picUrl;
    private String weight;
    private String height;
    private String BMI;
    private int seqAttendNum;
    private String age;
    private String facebookId;
    private int activation;
    private String utime;
    private String ctime;

    public MyData(int id, String username, String password, String nickname, String picUrl, String weight, String height, String BMI, int seqAttendNum, String age, String facebookId, int activation, String utime, String ctime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.picUrl = picUrl;
        this.weight = weight;
        this.height = height;
        this.BMI = BMI;
        this.seqAttendNum = seqAttendNum;
        this.age = age;
        this.facebookId = facebookId;
        this.activation = activation;
        this.utime = utime;
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", BMI='" + BMI + '\'' +
                ", seqAttendNum=" + seqAttendNum +
                ", age='" + age + '\'' +
                ", facebookId='" + facebookId + '\'' +
                ", activation=" + activation +
                ", utime='" + utime + '\'' +
                ", ctime='" + ctime + '\'' +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
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
