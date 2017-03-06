package com.google.slashb410.exgroup.model.group.group;

import java.util.ArrayList;

/**
 * Created by drizzle on 2017-03-05.
 */

public class ResGroupCalendar {

    ArrayList<InnerCalendar> result = new ArrayList<>();
    int resultCode;

    public ArrayList<InnerCalendar> getResult() {
        return result;
    }

    public void setResult(ArrayList<InnerCalendar> result) {
        this.result = result;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
