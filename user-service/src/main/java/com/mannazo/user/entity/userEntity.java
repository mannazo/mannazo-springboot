package com.mannazo.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class userEntity {

    @Id
    @GeneratedValue
    private int id;

    private String username;


}
