package com.kodezo.Dice_Roll.DAL.Services;

import com.kodezo.Dice_Roll.DAL.DTO.Room.NewRoomResponse;
import com.kodezo.Dice_Roll.DAL.Models.Room;
import com.kodezo.Dice_Roll.DAL.Models.User;

import com.kodezo.Dice_Roll.DAL.Repositories.RoomRepository;
import com.kodezo.Dice_Roll.Helpers.RoomCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public NewRoomResponse createRoom(String username) throws RoomNotFindException, UserAlreadyExistsException {
        Room room = new Room();
        room.setCode(generateRoomCode());
        this.roomRepository.save(room);
        String userId = joinRoom(room.getCode(), username);
        return new NewRoomResponse(room.getCode(), userId);
    }


    public String joinRoom(String code, String username) throws RoomNotFindException, UserAlreadyExistsException {

        Room room = getRoomByCode(code);

        User user = getUserByUsername(room, username);
        if(user != null){
            throw new UserAlreadyExistsException("User already exists");
        }

        user = new User(username, room.getUsers().size() == 0, room.getUsers().size() + 1);
        room.getUsers().add(user);
        this.roomRepository.save(room);

        return user.getId();
    }

    public User getUserByUsername(Room room, String username){
        return room.getUsers().stream().filter(u -> username.equals(u.getUsername())).findFirst().orElse(null);
    }

    public User getUserById(Room room, String userId){
        return room.getUsers().stream().filter(u -> userId.equals(u.getId())).findFirst().orElse(null);
    }

    public Room leaveRoom(String code, String userId) throws RoomNotFindException, UserNotExistsException {

        Room room = getRoomByCode(code);

        User user = getUserById(room, userId);
        if(user == null){
            throw new UserNotExistsException("User not exists");
        }

        room.getUsers().remove(user);

        if(room.getUsers().size() == 0){
            deleteRoom(code);
            return null;
        }

        if(user.isOwner()){
            room.getUsers().get(0).setOwner(true);
        }

        this.roomRepository.save(room);

        return room;
    }

    public void deleteRoom(String code) throws RoomNotFindException {
        Room room = getRoomByCode(code);
        this.roomRepository.delete(room);
    }

    public Room startGame(String code) throws RoomNotFindException, UserNotExistsException {
        Room room = getRoomByCode(code);
        room.setGame(true);
        room.setMoves(0);
        Optional<User> user = room.getUsers().stream().filter(u -> u.getMoveOrder() == 1).findFirst();
        if(user.isEmpty()) throw new UserNotExistsException("User not exists");
        user.get().setMoving(true);
        this.roomRepository.save(room);
        return room;
    }

    public Room nextMove(String code, String userId) throws RoomNotFindException, UserNotExistsException {
        Room room = getRoomByCode(code);
        User user = getUserById(room, userId);
        room.setMoves(room.getMoves() + 1);
        int actualOrder = user.getMoveOrder();
        int nextOrder = actualOrder % room.getUsers().size() + 1;
        Optional<User> nextUser = room.getUsers().stream().filter(u -> u.getMoveOrder() == nextOrder).findFirst();
        if(nextUser.isEmpty()) throw new UserNotExistsException("User not exists");
        nextUser.get().setMoving(true);
        user.setMoving(false);
        this.roomRepository.save(room);
        return room;
    }

    public Room getRoomByCode(String code) throws RoomNotFindException {
        Room room = this.roomRepository.findRoomByCodeIgnoreCase(code);
        if(room == null){
            throw new RoomNotFindException("Room not find");
        }
        return room;
    }

    private String generateRoomCode(){
        String code = RoomCodeGenerator.generateRandomRoomCode();
        if(this.roomRepository.findRoomByCodeIgnoreCase(code) != null){
            return generateRoomCode();
        }
        return code;
    }


    public static class RoomNotFindException extends Exception{
        public RoomNotFindException(String message){
            super(message);
        }
    }

    public static class UserAlreadyExistsException extends Exception{
        public UserAlreadyExistsException(String message){
            super(message);
        }
    }

    public static class UserNotExistsException extends Exception{
        public UserNotExistsException(String message){
            super(message);
        }
    }

}
