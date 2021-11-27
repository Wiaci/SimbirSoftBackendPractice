package com.example.simbirsoftbackendpractice.repository;

import com.example.simbirsoftbackendpractice.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    void deleteByName(String name);
    Room getByName(String name);

    @Modifying
    @Query("update Room set name=?2 where id=?1")
    void updateRoomName(Long id, String name);
}
