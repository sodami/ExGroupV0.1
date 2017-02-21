package com.google.slashb410.exgroup.model.group.home;

/**
 * Created by Tacademy on 2017-02-21.
 */

public class ResJoin {
    String message;
    ReqJoin data;
    int resultCode;

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

    public ReqJoin getData() {
        return data;
    }

    public void setData(ReqJoin data) {
        this.data = data;
    }


}
