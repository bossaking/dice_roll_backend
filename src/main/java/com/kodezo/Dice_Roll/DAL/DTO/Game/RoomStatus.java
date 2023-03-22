package com.kodezo.Dice_Roll.DAL.DTO.Game;

import com.kodezo.Dice_Roll.enums.ERoomStatus;

public class RoomStatus {
    private ERoomStatus roomStatus;
    private String userId, username;

    public RoomStatus(ERoomStatus roomStatus, String userId, String username) {
        this.roomStatus = roomStatus;
        this.userId = userId;
    }

    public ERoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(ERoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
