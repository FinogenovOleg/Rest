package com.example.Res.DTO;

import com.example.Res.models.UserEntity;

public class User {

    String name;
    String username;

    public User(UserEntity userEntity) {
        this.name = userEntity.getName();
        this.username = userEntity.getUsername();
    }

    @Override
    public String toString() {
        return "User{" + "\n" +
                "name='" + name + "'\n" +
                "username='" + username + "'\n" +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

