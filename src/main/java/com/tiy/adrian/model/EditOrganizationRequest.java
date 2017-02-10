package com.tiy.adrian.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbash on 2/10/2017.
 */
public class EditOrganizationRequest {
    private Long userId;
    private String email;
    private String password;
    private String name;
    private String address;
    private String city;
    private String state;
    private Integer zip;

    public EditOrganizationRequest() {
    }

    public EditOrganizationRequest(Long userId, String email, String password, String name, String address, String city, String state, Integer zip) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
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

}
