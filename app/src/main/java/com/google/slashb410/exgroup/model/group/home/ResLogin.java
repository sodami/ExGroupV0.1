package com.google.slashb410.exgroup.model.group.home;

/**
 * Created by Tacademy on 2017-02-20.
 */

public class ResLogin {
    private String message;
    private LoginUser user;
    private int resultCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public class LoginUser{
        private String username;
        private int activation;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getActivation() {
            return activation;
        }

        public void setActivation(int activation) {
            this.activation = activation;
        }
    }
}
