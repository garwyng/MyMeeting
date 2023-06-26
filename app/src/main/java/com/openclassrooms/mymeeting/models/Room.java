package com.openclassrooms.mymeeting.models;

import java.util.Date;
import java.util.List;

public class Room {

    private String roomName;

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}


