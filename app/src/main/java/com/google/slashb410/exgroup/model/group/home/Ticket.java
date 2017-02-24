package com.google.slashb410.exgroup.model.group.home;

/**
 * Created by Tacademy on 2017-02-24.
 */
public class Ticket{
    int userId;
    String ticketPeriod;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTicketPeriod() {
        return ticketPeriod;
    }

    public void setTicketPeriod(String ticketPeriod) {
        this.ticketPeriod = ticketPeriod;
    }
}