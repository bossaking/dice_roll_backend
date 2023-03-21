package com.kodezo.Dice_Roll.DAL.DTO.Room;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kodezo.Dice_Roll.DAL.DTO.User.UserDTO;
import com.kodezo.Dice_Roll.DAL.Models.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;
import java.util.UUID;

public class RoomDTO {

    private UUID Id;
    private String code;
    private int maxUsersCount;
    @JsonManagedReference
    private UserDTO owner;
    @JsonManagedReference
    private List<UserDTO> users;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getMaxUsersCount() {
        return maxUsersCount;
    }

    public void setMaxUsersCount(int maxUsersCount) {
        this.maxUsersCount = maxUsersCount;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
