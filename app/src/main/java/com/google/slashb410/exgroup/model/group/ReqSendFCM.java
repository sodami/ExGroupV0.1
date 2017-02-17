package com.google.slashb410.exgroup.model.group;

/**
 * Created by Tacademy on 2017-02-13.
 */

public class ReqSendFCM {
    String receiverId;
    String msg;

    public ReqSendFCM(String receiverId, String msg) {
        this.receiverId = receiverId;
        this.msg = msg;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
