package com.openclassrooms.mymeeting.di;

import com.openclassrooms.mymeeting.controler.MeetingRepository;
import com.openclassrooms.mymeeting.controler.MyMeetingApiService;

public class DI {
    /**
     * @return Repository
     */
    public static MeetingRepository getMeetingRepository(){
        return new MeetingRepository(MyMeetingApiService.getInstance());
    }
}
