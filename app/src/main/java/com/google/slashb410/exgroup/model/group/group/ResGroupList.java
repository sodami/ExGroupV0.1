package com.google.slashb410.exgroup.model.group.group;

import java.io.Serializable;

/**
 * Created by Tacademy on 2017-02-21.
 */

public class ResGroupList implements Serializable {
    private InnerGroupList result;
    private int resultCode;

    public InnerGroupList getResult() {
        return result;
    }

    public void setResult(InnerGroupList result) {
        this.result = result;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
