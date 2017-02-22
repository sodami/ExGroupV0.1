package com.google.slashb410.exgroup.model.group.group;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-22.
 */

public class ResGroupWaitingMember {

    ArrayList<WaitingMember> data;

    public ArrayList<WaitingMember> getData() {
        return data;
    }

    public void setData(ArrayList<WaitingMember> data) {
        this.data = data;
    }

    public class WaitingMember{

        String nickname;
        String picURL;

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPicURL() {
            return picURL;
        }

        public void setPicURL(String picURL) {
            this.picURL = picURL;
        }
    }
}
