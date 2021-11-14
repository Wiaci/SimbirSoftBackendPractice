package com.example.simbirsoftbackendpractice.repository;

import com.example.simbirsoftbackendpractice.domain.Blocking;
import com.example.simbirsoftbackendpractice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockingRepo extends JpaRepository<Blocking, Long> {
    void deleteAllByUser(User user);
}
