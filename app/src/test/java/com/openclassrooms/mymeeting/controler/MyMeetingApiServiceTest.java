package com.openclassrooms.mymeeting.controler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.openclassrooms.mymeeting.di.DI;
import com.openclassrooms.mymeeting.models.Meeting;
import com.openclassrooms.mymeeting.utils.Utils;

import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;
import java.util.List;

public class MyMeetingApiServiceTest extends MyMeetingApiService {

    MyMeetingApiService mApiService = DI.getMyMeetingApiService();

    List<String> roomsList;
    final int sizeExpected = 15;
    final int sizeRoomExpected = 10;


    @BeforeEach
    public void setUp() throws Exception {
        MyMeetingApiService mApiService = DI.getMyMeetingApiService();
    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void testDeleteMeeting() {
        List<Meeting> mMeetingsList = mApiService.getMeetingsList();
        Meeting userToDelete = mMeetingsList.get(0);
        mApiService.deleteMeeting(userToDelete);
        assertEquals(sizeExpected - 1, mMeetingsList.size());
    }
    @Test
    public void testRoomFilter() {
        List<Meeting> mMeetingsList = mApiService.getMeetingsList();
        String room = "Réunion 1";
        List<Meeting> listReturn = mApiService.roomFilter(room);
        assertEquals(4,listReturn.size());
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
        List<Meeting> listReturn = mApiService.DateFilter(dateToTest);
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
        assertEquals(sizeExpected, currentSize);
    }
}