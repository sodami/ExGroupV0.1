package com.google.slashb410.exgroup.model.group;

/**
 * Created by Tacademy on 2017-02-09.
 */

public class GroupInfo {

    private String groupName;
    private String groupImgPath;
    private int term;

    public GroupInfo() {
    }

    public GroupInfo(String groupName, String groupImgPath, int term) {
        this.groupName = groupName;
        this.groupImgPath = groupImgPath;
        this.term = term;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupImgPath() {
        return groupImgPath;
    }

    public void setGroupImgPath(String groupImgPath) {
        this.groupImgPath = groupImgPath;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }
}
