package com.example.alertingplatform.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Team {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private List<User> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public void addMember(User user) {
        members.add(user);
        user.setTeam(this);
    }
}
