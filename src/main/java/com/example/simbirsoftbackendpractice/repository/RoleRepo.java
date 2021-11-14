package com.example.simbirsoftbackendpractice.repository;

import com.example.simbirsoftbackendpractice.domain.Role;
import com.example.simbirsoftbackendpractice.domain.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role getByRole(RoleEnum role);
}
