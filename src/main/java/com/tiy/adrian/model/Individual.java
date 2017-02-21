package com.tiy.adrian.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbash on 2/10/2017.
 */
//@Entity
public class Individual {
    private String id;
    private String userId;
    private String email;
    private String password;
    private Long hostId;
    private String firstName;
    private String lastName;
    private List<String> eventIds = new ArrayList<>();

    public static Individual createTestIndividual() {
        List<Event> events = Event.createTestEvents();
        Individual individual = new Individual("777", "dominique@theironyard.com", "testpassword", 111L, "Dominique", "Bashizi", Event.getTestEventIds());
        return individual;
    }

    public Individual(String id, String email, String password, Long hostId, String firstName, String lastName, List<String> eventIds) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.hostId = hostId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eventIds = eventIds;
    }

    public Individual() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public void addEventId(String eventId) {
        eventIds.add(eventId);
    }

    public void removeEventId(Long eventId) {
        eventIds.remove(eventId);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getHostId() {
        return hostId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<String> eventIds) {
        this.eventIds = eventIds;
    }
}

