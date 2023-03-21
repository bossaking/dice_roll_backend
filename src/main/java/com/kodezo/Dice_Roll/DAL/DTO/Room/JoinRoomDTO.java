package com.kodezo.Dice_Roll.DAL.DTO.Room;

public class JoinRoomDTO {

    private String roomCode, username;

    public JoinRoomDTO(){

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
