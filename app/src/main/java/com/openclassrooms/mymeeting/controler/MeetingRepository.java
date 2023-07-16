package com.openclassrooms.mymeeting.controler;

import com.openclassrooms.mymeeting.models.Meeting;

import java.util.Date;
import java.util.List;

public class MeetingRepository {
    private final MyMeetingApiService mApiService;

    public static MyMeetingApiService getInstance(){
        return MyMeetingApiService.getInstance();
    }
    public MeetingRepository(MyMeetingApiService apiService) {
        mApiService = apiService;
    }

    public List<Meeting> getMeetingsList(){
        return mApiService.getMeetingsList();
    }
    public List<String> getRooms() {
        return mApiService.getRooms();
    }

    public void deleteMeeting(Meeting meeting) {
        mApiService.deleteMeeting(meeting);
    }
    public List<Meeting> dateFilter(Date date) {
        return mApiService.dateFilter(date);
    }
    public void addMeeting(Meeting meetingToAdd) {
        mApiService.addMeeting(meetingToAdd);
    }
    public List<Meeting> roomFilter(String room) {
        return mApiService.roomFilter(room);
    }
}
