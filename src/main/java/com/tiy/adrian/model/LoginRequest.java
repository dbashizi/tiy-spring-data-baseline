package com.tiy.adrian.model;

/**
 * Created by dbash on 2/10/2017.
 */
public class LoginRequest {
    private String email;
    private String password;

    public LoginRequest() {
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
}
