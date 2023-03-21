package com.kodezo.Dice_Roll.DAL.DTO.Room;

public class NewRoomResponse {

    public String code, userId;

    public NewRoomResponse(String code, String userId){
        this.code = code;
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
