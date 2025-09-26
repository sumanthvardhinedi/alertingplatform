package com.example.alertingplatform.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private Team team;

    public User(String name) {
        this.name = name;
    }
}
