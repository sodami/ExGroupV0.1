package com.google.slashb410.exgroup.model.group.group;

/**
 * Created by Tacademy on 2017-02-27.
 */

public class ReqComment {

    private int boardId;
    private String content;

    public ReqComment(int boardId, String content) {
        this.boardId = boardId;
        this.content = content;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
