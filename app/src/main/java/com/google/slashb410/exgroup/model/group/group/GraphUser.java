package com.google.slashb410.exgroup.model.group.group;

/**
 * Created by drizzle on 2017-03-14.
 */

public class GraphUser {

    private int user_id;
    private String date;
    private float lossweight;
    private String weight;
    private float firstWeight;

    public float getFirstWeight() {
        return firstWeight;
    }

    public void setFirstWeight(float firstWeight) {
        this.firstWeight = firstWeight;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getLossweight() {
        return lossweight;
    }

    public void setLossweight(float lossweight) {
        this.lossweight = lossweight;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }


}
