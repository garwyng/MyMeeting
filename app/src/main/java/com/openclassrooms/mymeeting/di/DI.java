package com.openclassrooms.mymeeting.di;

import com.openclassrooms.mymeeting.controler.MyMeetingApiService;

public class DI {
    private static final MyMeetingApiService mMyMeetingApiService = new MyMeetingApiService();
    public static MyMeetingApiService getMyMeetingApiService(){
        return mMyMeetingApiService;
    }

}
