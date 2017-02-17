package com.google.slashb410.exgroup.model.group;

/**
 * Created by Tacademy on 2017-02-13.
 */

public class ResSendFCM
{
    int code;
    String body;

    @Override
    public String toString() {
        return "ResSendFCM{" +
                "code=" + code +
                ", body='" + body + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
