package com.example.simbirsoftbackendpractice.service;

import com.example.simbirsoftbackendpractice.domain.Blocking;
import com.example.simbirsoftbackendpractice.domain.RoleEnum;
import com.example.simbirsoftbackendpractice.domain.Room;
import com.example.simbirsoftbackendpractice.domain.User;
import com.example.simbirsoftbackendpractice.exception.NoRightException;
import com.example.simbirsoftbackendpractice.repository.RoomRepo;
import com.example.simbirsoftbackendpractice.repository.UserRepo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class RoomService {

    private static final Logger logger = LogManager.getLogger(MessageService.class);

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    UserRepo userRepo;

    public Room getRoom(Long id) {
        return roomRepo.getById(id);
    }

    public void createRoom(Room room, Long creatorId) throws NoRightException {
        if (isBlocked(creatorId)) {
            throw new NoRightException();
        }
        roomRepo.save(room);
        logger.info("Room with id=" + room.getId() + "created by user with id=" + creatorId);
    }

    public void updateRoomName(Long roomId, String roomName, Long doerId) throws NoRightException {
        Room room = roomRepo.getById(roomId);
        if (!room.getOwner().equals(userRepo.getById(doerId)) || !checkRole(doerId, RoleEnum.ADMINISTRATOR)) {
            throw new NoRightException();
        }
        roomRepo.updateRoomName(roomId, roomName);
        logger.info("Room with id=" + roomId + " updated its name");
    }

    public void removeRoom(Long id) {
        roomRepo.deleteById(id);
        logger.info("Room with id=" + id + " deleted");
    }

    public void addUserInRoom(Long roomId, Long userId, Long doerId) throws NoRightException {
        if (isBlocked(doerId)) {
            throw new NoRightException();
        }
        Room room = roomRepo.getById(roomId);
        User user = userRepo.getById(userId);
        room.getUsers().add(user);
        logger.info("User with id=" + userId + " added in room with id="
                + roomId + " by user with id=" + doerId);
    }

    public void deleteUserFromRoom(Long roomId, Long userId, Long doerId) throws NoRightException {
        Room room = roomRepo.getById(roomId);
        if (!room.getOwner().equals(userRepo.getById(doerId)) || !checkRole(doerId, RoleEnum.ADMINISTRATOR)) {
            throw new NoRightException();
        }
        User user = userRepo.getById(userId);
        room.getUsers().remove(user);
        logger.info("User with id=" + userId + " deleted from room with id="
                + roomId + " by user with id=" + doerId);
    }

    private boolean isBlocked(Long id) {
        User user = userRepo.getById(id);
        Blocking blocking = user.getBlocking();
        return blocking != null;
    }

    private boolean checkRole(Long id, RoleEnum role) {
        User doer = userRepo.getById(id);
        return doer.getRole().getRole() == role;
    }
}
