package com.example.simbirsoftbackendpractice.repository;

import com.example.simbirsoftbackendpractice.domain.Role;
import com.example.simbirsoftbackendpractice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long> {

    @Modifying
    @Query("update User set name = ?2 where id = ?1")
    void updateUserName(Long id, String name);

    @Modifying
    @Query("update User set role = ?2 where id = ?1")
    void assignRole(Long id, Role role);

}
