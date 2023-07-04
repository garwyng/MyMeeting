

package com.openclassrooms.mymeeting.controler;


import com.openclassrooms.mymeeting.di.DI;
import com.openclassrooms.mymeeting.models.Meeting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyMeetingApiService implements MyMeetingApiServiceInterface {


    private static final List<String> mRoomsList = MyMeetingApiGenerator.getMeetingRooms();
    private static List<Meeting> mMeetingsList = MyMeetingApiGenerator.getMeetings();

    public List<Meeting> getMeetingsList() {

        return mMeetingsList;
    }

    public List<String> getRooms() {
        return mRoomsList;
    }


    @Override
    public void deleteMeeting(Meeting meeting) {
        DI.getMyMeetingApiService().getMeetingsList().remove(meeting);
    }

    /**
     *
     * @param date
     * @return List<Meeting>meetingOfTheDay
     */
    public List<Meeting> DateFilter(Date date) {

        List<Meeting> meetingOfTheDay = new ArrayList<Meeting>();
        for (Meeting meeting : mMeetingsList) {
            Date dateTest = meeting.getStartDate();
            Date dateProvided = date;
            if (dateTest.getTime() == dateProvided.getTime()) {
                meetingOfTheDay.add(meeting);
            }
        }
        return meetingOfTheDay;
    }

    public void addMeeting(Meeting meetingToAdd) {
        mMeetingsList.add(meetingToAdd);
    }

    public List<Meeting> roomFilter(String room) {
        List<Meeting> meetingsRoom = new ArrayList<Meeting>();
        for (Meeting meeting : mMeetingsList) {
            if (meeting.getRoom() == room) {
                meetingsRoom.add(meeting);
            }
        }
        return meetingsRoom;
    }
}
