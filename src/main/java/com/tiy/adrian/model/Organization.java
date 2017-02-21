package com.tiy.adrian.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbash on 2/10/2017.
 */
//@Entity
public class Organization {
    private String id;
    private String userId;
    private String email;
    private String password;
    private Long hostId;
    private String name;
    private String address;
    private String city;
    private String state;
    private Integer zip;
    private List<String> eventIds = new ArrayList<String>();

    public static Organization createTestOrganization() {
        Organization organization = new Organization("121", "organization1@theironyard.com", "testpassword", 444L, "Test Organization 1", "Test Address", "Atlanta", "GA", 30004, Event.getTestEventIds());
        return organization;
    }

    public Organization() {
    }

    public Organization(String id, String email, String password, Long hostId, String name, String address, String city, String state, Integer zip, List<String> eventIds) {
        this.id = id;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public List<String> getEventIds() {
        return eventIds;
    }

    public void setEventIds(List<String> eventIds) {
        this.eventIds = eventIds;
    }
}
