package com.tiy.adrian.model;

/**
 * Created by dbash on 2/10/2017.
 */
public class EventRequest {
    private Long userId;
    private Long eventId;

    public EventRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
