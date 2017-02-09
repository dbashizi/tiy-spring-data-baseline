package com.tiy.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by dbash on 2/9/2017.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String emailAddress;
    private String fullName;
}
