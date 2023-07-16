package com.openclassrooms.mymeeting.events;

import java.util.Date;

public class FilterMeetingEvent {
    public Date dateSearch;


    public FilterMeetingEvent(Date dateSearch) {
        this.dateSearch = dateSearch;
    }
}
