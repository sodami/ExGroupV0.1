package com.google.slashb410.exgroup.model.group.home;

/**
 * Created by Tacademy on 2017-02-21.
 */

public class ResInitInfo {
    private String result;
    private InnerInitInfo affectedData;
    private int resultCode;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public InnerInitInfo getAffectedData() {
        return affectedData;
    }

    public void setAffectedData(InnerInitInfo affectedData) {
        this.affectedData = affectedData;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
}
