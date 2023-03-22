package com.kodezo.Dice_Roll.DAL.Repositories;

import com.kodezo.Dice_Roll.DAL.Models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface RoomRepository extends MongoRepository<Room, String> {

    Room findRoomByCodeIgnoreCase(String code);

}
