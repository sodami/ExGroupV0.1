package com.google.slashb410.exgroup.model.group.group;

import java.util.ArrayList;

/**
 * Created by Tacademy on 2017-02-22.
 */

public class ResGroupSearch {

    ArrayList<GroupSearchData> result;
    int resultCode;

    public ArrayList<GroupSearchData> getResult() {
        return result;
    }

    public void setResult(ArrayList<GroupSearchData> result) {
        this.result = result;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}


