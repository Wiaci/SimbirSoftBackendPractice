package com.example.simbirsoftbackendpractice.repository;

import com.example.simbirsoftbackendpractice.domain.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepo extends JpaRepository<RoomType, Long> {
    RoomType getRoomTypeByName(String name);
}
