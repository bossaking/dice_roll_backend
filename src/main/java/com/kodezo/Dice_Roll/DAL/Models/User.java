package com.kodezo.Dice_Roll.DAL.Models;

import java.util.UUID;

public class User {


    private String id, username;
    private boolean isOwner;

    public User(String username, boolean isOwner){
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.isOwner = isOwner;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
