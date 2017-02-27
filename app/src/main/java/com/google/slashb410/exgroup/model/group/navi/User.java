package com.google.slashb410.exgroup.model.group.navi;

/**
 * Created by Tacademy on 2017-02-03.
 */

public class User
{
    String id;
    String email;
    String token;

    public User() {
    }

    public User(String id, String email, String token) {
        this.id = id;
        this.email = email;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
