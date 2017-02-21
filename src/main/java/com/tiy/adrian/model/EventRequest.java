package com.tiy.adrian.model;

/**
 * Created by dbash on 2/10/2017.
 */
public class EventRequest {
    private String userId;
    private String eventId;

    public EventRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
