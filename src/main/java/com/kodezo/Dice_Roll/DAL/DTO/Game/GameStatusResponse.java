package com.kodezo.Dice_Roll.DAL.DTO.Game;

import com.kodezo.Dice_Roll.DAL.Models.Room;
import com.kodezo.Dice_Roll.enums.EGameStatus;
import com.kodezo.Dice_Roll.enums.ERoomStatus;

public class GameStatusResponse {

    private EGameStatus gameStatus;
    private Room room;
    private int redValue, yellowValue, specialValue;
    public GameStatusResponse(EGameStatus gameStatus, Room room, int ...values) {
        this.gameStatus = gameStatus;
        this.room = room;
        if(values.length == 3) {
            this.redValue = values[0];
            this.yellowValue = values[1];
            this.specialValue = values[2];
        }
    }

    public EGameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(EGameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getRedValue() {
        return redValue;
    }

    public void setRedValue(int redValue) {
        this.redValue = redValue;
    }

    public int getYellowValue() {
        return yellowValue;
    }

    public void setYellowValue(int yellowValue) {
        this.yellowValue = yellowValue;
    }

    public int getSpecialValue() {
        return specialValue;
    }

    public void setSpecialValue(int specialValue) {
        this.specialValue = specialValue;
    }
}
