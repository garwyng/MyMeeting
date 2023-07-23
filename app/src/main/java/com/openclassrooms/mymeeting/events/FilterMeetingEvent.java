package com.openclassrooms.mymeeting.events;

import java.util.Date;

/**
 * Observer for a date selected to make a research of meetings
 */
public class FilterMeetingEvent {
    public Date dateSearch;


    public FilterMeetingEvent(Date dateSearch) {
        this.dateSearch = dateSearch;
    }
}
