package com.tiy.adrian.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbash on 2/10/2017.
 */
public class EditIndividualRequest {
    private Long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    public EditIndividualRequest(Long userId, String email, String password, String firstName, String lastName) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public EditIndividualRequest() {
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

}

