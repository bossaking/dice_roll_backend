package com.kodezo.Dice_Roll.DAL.Models;

import java.util.UUID;

public class User {


    private String id, username;
    private boolean isOwner;

    private int moveOrder;
    private boolean moving;

    public User(String username, boolean isOwner, int moveOrder){
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.isOwner = isOwner;
        this.moveOrder = moveOrder;
        this.moving = false;
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

    public int getMoveOrder() {
        return moveOrder;
    }

    public void setMoveOrder(int moveOrder) {
        this.moveOrder = moveOrder;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
