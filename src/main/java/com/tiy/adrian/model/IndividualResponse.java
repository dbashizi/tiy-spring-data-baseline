package com.tiy.adrian.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbash on 2/10/2017.
 */
public class IndividualResponse {
    private Individual individual;
    private List<Event> events = new ArrayList<Event>();
    private ErrorInformation errorInformation;

    public static IndividualResponse createTestIndividualResponse() {
        IndividualResponse individualResponse = new IndividualResponse();
        individualResponse.setEvents(Event.createTestEvents());
        individualResponse.setIndividual(Individual.createTestIndividual());

        return individualResponse;
    }

    public IndividualResponse() {
    }

    public ErrorInformation getErrorInformation() {
        return errorInformation;
    }

    public void setErrorInformation(ErrorInformation errorInformation) {
        this.errorInformation = errorInformation;
    }

    public Individual getIndividual() {
        return individual;
    }

    public void setIndividual(Individual individual) {
        this.individual = individual;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
