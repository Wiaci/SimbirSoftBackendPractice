package com.example.simbirsoftbackendpractice.repository;

import com.example.simbirsoftbackendpractice.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoomRepo extends JpaRepository<Room, Long> {

    @Modifying
    @Query("update Room set name=?2 where id=?1")
    void updateRoomName(Long id, String name);
}
