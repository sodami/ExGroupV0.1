package com.google.slashb410.exgroup.model.group.group;

import java.util.ArrayList;

/**
 * Created by drizzle on 2017-03-13.
 */

public class ResRank {

    private Data result;
    private int resultCode;

    public Data getResult() {
        return result;
    }

    public void setResult(Data result) {
        this.result = result;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public class Data{

        ArrayList<RankUser> data = new ArrayList<RankUser>();

        public ArrayList<RankUser> getData() {
            return data;
        }

        public void setData(ArrayList<RankUser> data) {
            this.data = data;
        }


    }
}
