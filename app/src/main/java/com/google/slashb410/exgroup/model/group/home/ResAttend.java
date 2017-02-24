package com.google.slashb410.exgroup.model.group.home;

/**
 * Created by Tacademy on 2017-02-24.
 */

public class ResAttend {

    private String message;
    private Ticket ticket;
    private int resultCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }


}
