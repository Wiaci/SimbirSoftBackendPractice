package com.example.simbirsoftbackendpractice.service;

import com.example.simbirsoftbackendpractice.domain.Role;
import com.example.simbirsoftbackendpractice.domain.User;
import com.example.simbirsoftbackendpractice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User getUser(Long id) {
        return userRepo.getById(id);
    }

    public void addUser(User user) {
        userRepo.save(user);
    }

    public void updateUserName(Long id, String name) {
        userRepo.updateUserName(id, name);
    }

    public void assignRole(Long id, Role role) {
        userRepo.assignRole(id, role);
    }

    public void removeUser(Long id) {
        userRepo.deleteById(id);
    }
}
