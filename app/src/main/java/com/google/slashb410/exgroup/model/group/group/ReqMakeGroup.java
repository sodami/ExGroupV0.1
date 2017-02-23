package com.google.slashb410.exgroup.model.group.group;

/**
 * Created by Tacademy on 2017-02-23.
 */

public class ReqMakeGroup {

    String groupTitle;
    int maxNum;
    int exPeriod;
    String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getExPeriod() {
        return exPeriod;
    }

    public void setExPeriod(int exPeriod) {
        this.exPeriod = exPeriod;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }


}
