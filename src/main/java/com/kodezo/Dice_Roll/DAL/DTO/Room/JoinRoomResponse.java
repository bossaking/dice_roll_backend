package com.kodezo.Dice_Roll.DAL.DTO.Room;

public class JoinRoomResponse {
    private String userId;

    public JoinRoomResponse(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
