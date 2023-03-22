package com.kodezo.Dice_Roll.DAL.DTO.Room;

public class LeaveRoomDTO {

    private String roomCode, userId;

    public LeaveRoomDTO(){

    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
