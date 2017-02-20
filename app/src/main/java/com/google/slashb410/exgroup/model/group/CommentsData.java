package com.google.slashb410.exgroup.model.group;

import java.util.ArrayList;

/**
 * Created by drizzle on 2017-02-18.
 */

public class CommentsData {


    private ArrayList<InnerCommentData> data= new ArrayList<>();

    public ArrayList<InnerCommentData> getData() {
        return data;
    }

    public void setData(ArrayList<InnerCommentData> data) {
        this.data = data;
    }


}
