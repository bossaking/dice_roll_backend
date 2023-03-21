package com.kodezo.Dice_Roll.DAL.DTO.Room;

public class LeaveRoomDTO {

    private String roomCode, username;

    public LeaveRoomDTO(){

    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
