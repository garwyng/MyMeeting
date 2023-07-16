package com.openclassrooms.mymeeting.controler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.mymeeting.models.Meeting;
import com.openclassrooms.mymeeting.utils.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class MyMeetingApiServiceTest {
    List<Meeting> mMeetingsList;
    private MyMeetingApiService mApiService;
    List<String> roomsList;
    final int sizeExpected = 15;
    final int sizeRoomExpected = 10;


    @Before
    public void setUp() throws Exception {
        mApiService= null;
        mApiService = MyMeetingApiService.getInstance();
        mMeetingsList = mApiService.getMeetingsList();
    }

    @After
    public void tearDown() throws Exception {
        mApiService = null;
    }
    @Test
    public void testDeleteMeeting() {
        mMeetingsList=mApiService.getMeetingsList();
        Meeting userToDelete = mApiService.getMeetingsList().get(10);
        mApiService.deleteMeeting(userToDelete);
        assertEquals(sizeExpected - 1, mMeetingsList.size());
    }
    @Test
    public void testRoomFilter() {
        List<Meeting> mMeetingsList = mApiService.getMeetingsList();
        String room = "Réunion 1";
        List<Meeting> listReturn = mApiService.roomFilter(room);
        assertEquals(3,listReturn.size());
        for (Meeting meeting:listReturn) {assertTrue(mMeetingsList.contains(meeting));}
    }
    @Test
    public void testAddMeeting() {
        List<Meeting> mMeetingsList = mApiService.getMeetingsList();
        //set variable to use for userToAdd
        int currentSize = mMeetingsList.size();
        int id = 16;
        String subject = "Test Application";
        List<String> guest = mApiService.getMeetingsList().get(0).getGuests();
        String room = mApiService.getRooms().get(0);
        Date dateStart = Utils.getDateFromString("01/07/2023 09:00");
        Date dateStop = Utils.getDateFromString("01/07/2023 11:00");
        //creation of userToAdd
        Meeting meetingToAdd = new Meeting(id,subject,guest,room,dateStart,dateStop);
        //execution of addMeeting
        mApiService.addMeeting(meetingToAdd);
        //assertion
        assertEquals(currentSize +1,mMeetingsList.size());
        assertTrue(mMeetingsList.contains(meetingToAdd));
    }
    @Test
    public void dateFilter() {
        List<Meeting> mMeetingsList = mApiService.getMeetingsList();
        Date dateToTest = Utils.getDateFromString("01/08/23 09:00");
        List<Meeting> listReturn = mApiService.dateFilter(dateToTest);
        assertEquals(4,listReturn.size());
        for (Meeting meeting:listReturn) {assertTrue(mMeetingsList.contains(meeting));}
    }
    @Test
    public void testGetRooms() {
        roomsList = mApiService.getRooms();
        assertEquals(sizeRoomExpected, roomsList.size());
    }
    @Test
    public void testGetMeetingsList() {
        List<Meeting> mMeetingsList = mApiService.getMeetingsList();
        int currentSize = mMeetingsList.size();
        assertEquals(sizeExpected -1, currentSize); //delete meeting étant executé en premier la lise ne contient plus que 14 reference
    }
}