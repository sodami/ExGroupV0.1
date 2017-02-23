package com.google.slashb410.exgroup.model.group.group;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-23.
 */

public class ResUncertifiedMem {

    ArrayList<MemSimpleData> data;

    @Override
    public String toString() {
        return "ResUncertifiedMem{" +
                "data=" + data +
                '}';
    }

    public ArrayList<MemSimpleData> getData() {
        return data;
    }

    public void setData(ArrayList<MemSimpleData> data) {
        this.data = data;
    }

    public class MemSimpleData{
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
