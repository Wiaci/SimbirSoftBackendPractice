package com.example.simbirsoftbackendpractice.service;

import com.example.simbirsoftbackendpractice.domain.Room;
import com.example.simbirsoftbackendpractice.repository.RoomRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class RoomService {

    @Autowired
    private RoomRepo roomRepo;

    public Room getRoom(Long id) {
        return roomRepo.getById(id);
    }

    public void createRoom(Room room) {
        roomRepo.save(room);
    }

    public void updateRoomName(Long id, String roomName) {
        roomRepo.updateRoomName(id, roomName);
    }

    public void removeRoom(Long id) {
        roomRepo.deleteById(id);
    }
}
