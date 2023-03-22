package com.kodezo.Dice_Roll.DAL.DTO.Game;

import com.kodezo.Dice_Roll.DAL.Models.Room;
import com.kodezo.Dice_Roll.enums.ERoomStatus;

public class RoomStatusResponse {

    private ERoomStatus roomStatus;
    private Room room;
    private String userId, username;

    public RoomStatusResponse(ERoomStatus roomStatus, Room room, String userId, String username) {
        this.roomStatus = roomStatus;
        this.room = room;
        this.userId = userId;
        this.username = username;
    }

    public ERoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(ERoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
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
