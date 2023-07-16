package com.openclassrooms.mymeeting.events;

import com.openclassrooms.mymeeting.models.Meeting;

public class DeleteMeetingEvent {

    public Meeting meeting;


    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }
}
