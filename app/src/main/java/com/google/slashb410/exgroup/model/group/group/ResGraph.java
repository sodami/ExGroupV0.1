package com.google.slashb410.exgroup.model.group.group;

import java.util.ArrayList;

/**
 * Created by drizzle on 2017-03-14.
 */

public class ResGraph {
    private ArrayList<InnerGraph> result = new ArrayList<>();
    private int resultCode;

    public ArrayList<InnerGraph> getResult() {
        return result;
    }

    public void setResult(ArrayList<InnerGraph> result) {
        this.result = result;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public class InnerGraph{

        private ArrayList<GraphUser> graphUsers = new ArrayList<GraphUser>();

        public ArrayList<GraphUser> getGraphUsers() {
            return graphUsers;
        }

        public void setGraphUsers(ArrayList<GraphUser> graphUsers) {
            this.graphUsers = graphUsers;
        }
    }
}
