

package com.openclassrooms.mymeeting.controler;

import android.util.Log;


import com.openclassrooms.mymeeting.di.DI;
import com.openclassrooms.mymeeting.models.Meeting;
import com.openclassrooms.mymeeting.models.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyMeetingApiService implements MyMeetingApiServiceInterface {


    private static final List<Room> mRoomsList = MyMeetingApiGenerator.getMeetingRooms();
    private static List<Meeting> mMeetingsList = MyMeetingApiGenerator.getMeetings();

    public List<Meeting> getMeetingsList() {
        return mMeetingsList;
    }

    public static List<Room> getRooms() {return mRoomsList;
    }


    @Override
    public void deleteMeeting(Meeting meeting) {
        Log.d("MEETINGSLIST", "deleteMeeting: "+ mMeetingsList);
        DI.getMyMeetingApiService().getMeetingsList().remove(meeting);
        Log.d("MEETINGSLIST", "deleteMeeting: "+ mMeetingsList);
    }

    public List<Meeting> DateFilter(Date date) {
         List<Meeting> meetingOfTheDay = new ArrayList<Meeting>();
        for (Meeting meeting:mMeetingsList
        ) {if (meeting.getStartDate() == date ){
            meetingOfTheDay.add(meeting);
        }
        } mMeetingsList=meetingOfTheDay;
        return mMeetingsList;
    }
    public void addMeeting(Meeting meetingToAdd){
        mMeetingsList.add(meetingToAdd);
    }
}
