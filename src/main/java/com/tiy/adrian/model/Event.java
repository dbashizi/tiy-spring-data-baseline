package com.tiy.adrian.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dbash on 2/10/2017.
 */
//@Entity
public class Event {
    private String id;
    private String eventId;
    private String name;
    private String type;
    private String host;
    private String hostId;
    private String photo;
    private String location;
    private String address;
    private String city;
    private String state;
    private Integer zip;
    private Date startTime;
    private Date endTime;
    private String details;
    private List<String> userIds = new ArrayList<>();

    public static List<Event> createTestEvents() {
        List<String> testUserIds = new ArrayList<>();
        testUserIds.add("777");
        testUserIds.add("888");
        testUserIds.add("999");
        Event firstEvent = new Event("111", "HC Event 1", "TEST_TYPE_1", "Test Host 1", "444", null, "Test Location 1", "Test Address 1", "Atlanta", "GA", 30004, new Date(), new Date(), "Test Details 1", testUserIds);
        Event secondEvent = new Event("222", "HC Event 2", "TEST_TYPE_1", "Test Host 1", "444", null, "Test Location 2", "Test Address 2", "Atlanta", "GA", 30004, new Date(), new Date(), "Test Details 2", testUserIds);
        Event thirdEvent = new Event("222", "HC Event 3", "TEST_TYPE_1", "Test Host 2", "555", null, "Test Location 3", "Test Address 3", "Atlanta", "GA", 30004, new Date(), new Date(), "Test Details 3", testUserIds);
        List<Event> events = new ArrayList<Event>();
        events.add(firstEvent);
        events.add(secondEvent);
        events.add(thirdEvent);
        return events;
    }

    public static List<String> getTestEventIds() {
        List<Event> events = createTestEvents();
        List<String> eventIds = new ArrayList<>();
        for (Event event : events) {
            eventIds.add(event.getId());
        }

        return eventIds;
    }

    public void addUserId(String userId) {
        userIds.add(userId);
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Event(String id, String name, String type, String host, String hostId, String photo, String location, String address, String city, String state, Integer zip, Date startTime, Date endTime, String details, List<String> userIds) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.host = host;
        this.hostId = hostId;
        this.photo = photo;
        this.location = location;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.startTime = startTime;
        this.endTime = endTime;
        this.details = details;
        this.userIds = userIds;
    }

    public Event() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
