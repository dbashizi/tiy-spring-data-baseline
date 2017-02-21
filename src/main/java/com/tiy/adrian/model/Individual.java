package com.tiy.adrian.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbash on 2/10/2017.
 */
//@Entity
public class Individual {
    @Id
    @GeneratedValue
    private Long userId;
    private String email;
    private String password;
    private Long hostId;
    private String firstName;
    private String lastName;
    private List<Long> eventIds = new ArrayList<Long>();

    public static Individual createTestIndividual() {
        List<Event> events = Event.createTestEvents();
        Individual individual = new Individual(777L, "dominique@theironyard.com", "testpassword", 111L, "Dominique", "Bashizi", Event.getTestEventIds());
        return individual;
    }

    public Individual(Long userId, String email, String password, Long hostId, String firstName, String lastName, List<Long> eventIds) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.hostId = hostId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eventIds = eventIds;
    }

    public Individual() {
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public void addEventId(Long eventId) {
        eventIds.add(eventId);
    }

    public void removeEventId(Long eventId) {
        eventIds.remove(eventId);
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public long getHostId() {
        return hostId;
    }

    public void setHostId(long hostId) {
        this.hostId = hostId;
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

    public List<Long> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<Long> eventIds) {
        this.eventIds = eventIds;
    }
}

