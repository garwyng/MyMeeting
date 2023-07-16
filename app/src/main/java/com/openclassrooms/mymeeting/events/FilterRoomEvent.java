package com.openclassrooms.mymeeting.events;

public class FilterRoomEvent {
    public String roomSelected;


    public FilterRoomEvent(String roomSelected) {
        this.roomSelected = roomSelected;
    }
}
