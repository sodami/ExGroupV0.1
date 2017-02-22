package com.google.slashb410.exgroup.model.group.group;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-21.
 */

public class InnerGroupList {
    ArrayList<GroupData> actRst;
    ArrayList<GroupData> unActRst;

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
