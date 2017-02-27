package com.google.slashb410.exgroup.model.group.group;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-21.
 */

public class InnerGroupList implements Serializable{
    private ArrayList<GroupData> actRst = new ArrayList<>();
    private ArrayList<GroupData> waitRst = new ArrayList<>();
    private ArrayList<GroupData> unActRst = new ArrayList<>();

    public ArrayList<GroupData> getWaitRst() {
        return waitRst;
    }

    public void setWaitRst(ArrayList<GroupData> waitRst) {
        this.waitRst = waitRst;
    }

    public ArrayList<GroupData> getActRst() {
        return actRst;
    }

    public void setActRst(ArrayList<GroupData> actRst) {
        this.actRst = actRst;
    }

    public ArrayList<GroupData> getUnActRst() {
        return unActRst;
    }

    public void setUnActRst(ArrayList<GroupData> unActRst) {
        this.unActRst = unActRst;
    }
}
