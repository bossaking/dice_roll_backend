package com.kodezo.Dice_Roll.Controllers;

import com.kodezo.Dice_Roll.DAL.DTO.Game.GameStatus;
import com.kodezo.Dice_Roll.DAL.DTO.Game.GameStatusResponse;
import com.kodezo.Dice_Roll.DAL.DTO.Game.RoomStatus;
import com.kodezo.Dice_Roll.DAL.DTO.Game.RoomStatusResponse;
import com.kodezo.Dice_Roll.DAL.DTO.Room.*;
import com.kodezo.Dice_Roll.DAL.Models.Room;
import com.kodezo.Dice_Roll.DAL.Models.User;
import com.kodezo.Dice_Roll.DAL.Services.RoomService;
import com.kodezo.Dice_Roll.Helpers.DiceValueGenerator;
import com.kodezo.Dice_Roll.enums.ERoomStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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

        String userId;

        try {

            userId = this.roomService.joinRoom(joinRoomDTO.getRoomCode(), joinRoomDTO.getUsername());

        } catch (RoomService.RoomNotFindException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect room code");

        } catch (RoomService.UserAlreadyExistsException e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is taken");
        }

        JoinRoomResponse joinRoomResponse = new JoinRoomResponse(userId);

        return ResponseEntity.ok().body(joinRoomResponse);
    }

    @PostMapping("/room/leave")
    public ResponseEntity<?> leaveRoom(@RequestBody LeaveRoomDTO leaveRoomDTO) {

        try {
            this.roomService.leaveRoom(leaveRoomDTO.getRoomCode(), leaveRoomDTO.getUserId());
        } catch (RoomService.RoomNotFindException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect room code");
        } catch (RoomService.UserNotExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not exists");
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/room")
    public ResponseEntity<?> getAllRooms(@RequestParam String roomCode) {
        Room room;
        try {
            room = this.roomService.getRoomByCode(roomCode);
        } catch (RoomService.RoomNotFindException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect room code");
        }

        return ResponseEntity.ok(room);
    }

    @MessageMapping("/{roomCode}/status")
    @SendTo("/room/{roomCode}/status")
    public RoomStatusResponse roomStatusChanged(@DestinationVariable String roomCode, RoomStatus roomStatus) {
        try {
            Room room = this.roomService.getRoomByCode(roomCode);
            User user = this.roomService.getUserById(room, roomStatus.getUserId());

            switch (roomStatus.getRoomStatus()){
                case USER_LEAVE -> {
                    room = this.roomService.leaveRoom(roomCode, user.getId());
                }
                case GAME_START -> {
                    room = this.roomService.startGame(roomCode);
                }
            }

            return new RoomStatusResponse(roomStatus.getRoomStatus(), room, user.getId(), user.getUsername());

        } catch (RoomService.RoomNotFindException | RoomService.UserNotExistsException e) {
            throw new RuntimeException(e);
        }
    }

    @MessageMapping("/{roomCode}/roll")
    @SendTo("/room/{roomCode}/roll")
    public GameStatusResponse gameRoll(@DestinationVariable String roomCode, GameStatus gameStatus) {
        try {
            Room room = this.roomService.getRoomByCode(roomCode);
            switch (gameStatus.getGameStatus()){

                case RESULT -> {
                    int redValue = DiceValueGenerator.Generate(1, 6);
                    int yellowValue = DiceValueGenerator.Generate(1, 6);
                    int specialValue = DiceValueGenerator.Generate(1, 6);
                    return new GameStatusResponse(gameStatus.getGameStatus(), room, redValue, yellowValue, specialValue);
                }

                case NEXT_MOVE -> {
                    room = this.roomService.nextMove(roomCode, gameStatus.getUserId());
                }
            }

            return new GameStatusResponse(gameStatus.getGameStatus(), room);

        } catch (RoomService.RoomNotFindException | RoomService.UserNotExistsException e) {
            throw new RuntimeException(e);
        }
    }

}
