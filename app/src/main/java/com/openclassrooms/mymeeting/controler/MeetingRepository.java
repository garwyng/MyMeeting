package com.openclassrooms.mymeeting.controler;

import com.openclassrooms.mymeeting.models.Meeting;

import java.util.Date;
import java.util.List;

public class MeetingRepository {
    private final MyMeetingApiService mApiService;

    public static MyMeetingApiService getInstance(){
        return MyMeetingApiService.getInstance();
    }


    private MeetingRepository(MyMeetingApiService apiService) {
        mApiService = apiService;
    }

    private List<Meeting> getMeetingsList(){
        return mApiService.getMeetingsList();
    }

    private List<String> getRooms() {
        return mApiService.getRooms();
    }

    private void deleteMeeting(Meeting meeting) {
        mApiService.deleteMeeting(meeting);
    }

    private List<Meeting> dateFilter(Date date) {
        return mApiService.dateFilter(date);
    }
    private void addMeeting(Meeting meetingToAdd) {
        mApiService.addMeeting(meetingToAdd);
    }

    private List<Meeting> roomFilter(String room) {
        return mApiService.roomFilter(room);
    }
}
