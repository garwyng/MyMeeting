package com.openclassrooms.mymeeting.models;

import java.util.Date;
import java.util.List;

public class Meeting {
    int id;
    private String subject;
    private List<String> guests;
    private Room room;
    private Date startDate;
    private Date endDate;

    /**
     *
     * @param subject
     * @param guests
     */
    public Meeting(int id, String subject, List<String> guests, Room room, Date startDate, Date endDate) {
        this.id = id;
        this.subject = subject;
        this.guests = guests;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getGuests() {
        return guests;
    }

    public void setGuests(List<String> guests) {
        this.guests = guests;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
