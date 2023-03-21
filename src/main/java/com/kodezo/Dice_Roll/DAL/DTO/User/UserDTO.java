package com.kodezo.Dice_Roll.DAL.DTO.User;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kodezo.Dice_Roll.DAL.DTO.Room.RoomDTO;
import com.kodezo.Dice_Roll.DAL.Models.Room;
import jakarta.persistence.*;

import java.util.UUID;

public class UserDTO {

    private UUID id;
    private String nickname;
    @JsonBackReference
    private RoomDTO room;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }
}
