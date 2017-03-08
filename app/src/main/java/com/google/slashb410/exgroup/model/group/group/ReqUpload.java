package com.google.slashb410.exgroup.model.group.group;

/**
 * Created by Tacademy on 2017-02-27.
 */

public class ReqUpload {

    private int groupId;
    private int categoryNum;
    private String content;
    private String summary;

    public ReqUpload(int groupId, int categoryNum, String content, String summary, String photo) {
        this.groupId = groupId;
        this.categoryNum = categoryNum;
        this.content = content;
        this.summary = summary;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getCategoryNum() {
        return categoryNum;
    }

    public void setCategoryNum(int categoryNum) {
        this.categoryNum = categoryNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}
