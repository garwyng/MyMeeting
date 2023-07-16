package com.openclassrooms.mymeeting.controler;


import com.openclassrooms.mymeeting.models.Meeting;

import java.util.List;

public interface MyMeetingApiServiceInterface {
    void deleteMeeting(Meeting meeting);

    List<Meeting> getMeetingsList();

    void addMeeting(Meeting meetingToAdd);

    List<String> getRooms();

}
