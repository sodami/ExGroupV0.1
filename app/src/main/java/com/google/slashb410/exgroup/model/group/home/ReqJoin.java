package com.google.slashb410.exgroup.model.group.home;

import java.io.Serializable;

/**
 * Created by Tacademy on 2017-02-21.
 */

public class ReqJoin implements Serializable {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}