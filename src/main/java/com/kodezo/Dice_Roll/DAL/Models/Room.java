package com.kodezo.Dice_Roll.DAL.Models;

import com.kodezo.Dice_Roll.Controllers.RoomController;
import jakarta.persistence.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document("rooms")
public class Room {

    @Id
    private String id;

    private String code;

    private boolean game;

    private int moves = 0;

    private List<User> users = new ArrayList<>();

    public Room(String id, String code, List<User> users){
        this.id = id;
        this.code = code;
        this.users = users;
        this.game = false;
    }
    public Room(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }
}
