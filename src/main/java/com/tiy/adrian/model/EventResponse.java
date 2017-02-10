package com.tiy.adrian.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbash on 2/10/2017.
 */
public class EventResponse {
    private ErrorInformation errorInformation;
    private List<Event> events = new ArrayList<Event>();

    public static EventResponse creasteTestEventResponse() {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setEvents(Event.createTestEvents());
        return eventResponse;
    }

    public EventResponse() {
    }

    public ErrorInformation getErrorInformation() {
        return errorInformation;
    }

    public void setErrorInformation(ErrorInformation errorInformation) {
        this.errorInformation = errorInformation;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
