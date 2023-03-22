package com.kodezo.Dice_Roll.DAL.DTO.Game;

import com.kodezo.Dice_Roll.enums.EGameStatus;
import com.kodezo.Dice_Roll.enums.ERoomStatus;

public class GameStatus {
    private EGameStatus gameStatus;
    private String userId, username;



    public GameStatus(EGameStatus gameStatus, String userId) {
        this.gameStatus = gameStatus;
        this.userId = userId;
    }

    public EGameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(EGameStatus roomStatus) {
        this.gameStatus = roomStatus;
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
