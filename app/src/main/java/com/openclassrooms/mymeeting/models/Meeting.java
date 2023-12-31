package com.openclassrooms.mymeeting.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.List;

public class Meeting implements Parcelable {
    int id;
    private String subject;
    private List<String> guests;
    private String room;
    private Date startDate;
    private Date endDate;


    public Meeting(int id, String subject, List<String> guests, String room, Date startDate, Date endDate) {
        this.id = id;
        this.subject = subject;
        this.guests = guests;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    protected Meeting(Parcel in) {
        id = in.readInt();
        subject = in.readString();
        guests = in.createStringArrayList();
        room = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(subject);
        dest.writeStringList(guests);
        dest.writeString(room);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
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
