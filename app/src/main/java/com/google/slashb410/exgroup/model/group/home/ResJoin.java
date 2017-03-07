package com.google.slashb410.exgroup.model.group.home;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-21.
 */


public class ResJoin implements Serializable{
    private String message;
    private ArrayList<ReqJoin> data = new ArrayList<>();
    private int resultCode;

    @Override
    public String toString() {
        return "ResJoin{" +
                "message='" + message + '\'' +
                ", data=" + data +
                ", resultCode=" + resultCode +
                '}';
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ReqJoin> getData() {
        return data;
    }

    public void setData(ArrayList<ReqJoin> data) {
        this.data = data;
    }
}

