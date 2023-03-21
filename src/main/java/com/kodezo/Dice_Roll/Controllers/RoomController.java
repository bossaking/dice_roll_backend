package com.kodezo.Dice_Roll.Controllers;

import com.kodezo.Dice_Roll.DAL.DTO.Room.JoinRoomDTO;
import com.kodezo.Dice_Roll.DAL.DTO.Room.LeaveRoomDTO;
import com.kodezo.Dice_Roll.DAL.DTO.Room.NewRoomDTO;
import com.kodezo.Dice_Roll.DAL.DTO.Room.NewRoomResponse;
import com.kodezo.Dice_Roll.DAL.Models.Room;
import com.kodezo.Dice_Roll.DAL.Services.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomController {

    private final RoomService roomService;
    private final ModelMapper modelMapper;

    @Autowired
    public RoomController(RoomService roomService, ModelMapper modelMapper) {
        this.roomService = roomService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/room")
    public ResponseEntity<?> createRoom(@RequestBody NewRoomDTO newRoomDTO) {
        NewRoomResponse room;
        try {
            room = this.roomService.createRoom(newRoomDTO.getUsername());
        } catch (RoomService.RoomNotFindException | RoomService.UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        }

        return ResponseEntity.ok(room);
    }

    @PostMapping("/room/join")
    public ResponseEntity<?> joinRoom(@RequestBody JoinRoomDTO joinRoomDTO) {

        try {

            this.roomService.joinRoom(joinRoomDTO.getRoomCode(), joinRoomDTO.getUsername());

        } catch (RoomService.RoomNotFindException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect room code");

        } catch (RoomService.UserAlreadyExistsException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is taken");
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/room/leave")
    public ResponseEntity<?> leaveRoom(@RequestBody LeaveRoomDTO leaveRoomDTO){

        try {
            this.roomService.leaveRoom(leaveRoomDTO.getRoomCode(), leaveRoomDTO.getUsername());
        } catch (RoomService.RoomNotFindException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect room code");
        } catch (RoomService.UserNotExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not exists");
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/room")
    public ResponseEntity<?> getAllRooms(@RequestParam String roomCode){
        Room room;
        try {
            room = this.roomService.getRoomByCode(roomCode);
        } catch (RoomService.RoomNotFindException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect room code");
        }

        return ResponseEntity.ok(room);
    }

}
