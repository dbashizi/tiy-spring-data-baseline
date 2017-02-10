package com.tiy.adrian.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbash on 2/10/2017.
 */
public class Organization {
    private Long userId;
    private String email;
    private String password;
    private Long hostId;
    private String name;
    private String address;
    private String city;
    private String state;
    private Integer zip;
    private List<Long> eventIds = new ArrayList<Long>();

    public static Organization createTestOrganization() {
        Organization organization = new Organization(121L, "organization1@theironyard.com", "testpassword", 444L, "Test Organization 1", "Test Address", "Atlanta", "GA", 30004, Event.getTestEventIds());
        return organization;
    }

    public Organization() {
    }

    public Organization(Long userId, String email, String password, Long hostId, String name, String address, String city, String state, Integer zip, List<Long> eventIds) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.hostId = hostId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.eventIds = eventIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Long> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<Long> eventIds) {
        this.eventIds = eventIds;
    }
}
