package com.google.slashb410.exgroup.model.group;

/**
 * Created by Tacademy on 2017-02-16.
 */

public class SearchData {

    @Override
    public String toString() {
        return "SearchData{" +
                "result=" + result +
                '}';
    }

    private InnerSearchData result;

    public SearchData() {
    }

    public SearchData(InnerSearchData result) {
        this.result = result;
    }

    public InnerSearchData getResult() {
        return result;
    }

    public void setResult(InnerSearchData result) {
        this.result = result;
    }

}

