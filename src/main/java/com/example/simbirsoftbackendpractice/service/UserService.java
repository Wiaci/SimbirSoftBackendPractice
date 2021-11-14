package com.example.simbirsoftbackendpractice.service;

import com.example.simbirsoftbackendpractice.domain.Blocking;
import com.example.simbirsoftbackendpractice.domain.Role;
import com.example.simbirsoftbackendpractice.domain.RoleEnum;
import com.example.simbirsoftbackendpractice.domain.User;
import com.example.simbirsoftbackendpractice.exception.NoRightException;
import com.example.simbirsoftbackendpractice.repository.BlockingRepo;
import com.example.simbirsoftbackendpractice.repository.RoleRepo;
import com.example.simbirsoftbackendpractice.repository.UserRepo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    UserRepo userRepo;

    @Autowired
    BlockingRepo blockingRepo;

    @Autowired
    RoleRepo roleRepo;

    public User getUser(Long id) {
        return userRepo.getById(id);
    }

    public void addUser(User user) {
        userRepo.save(user);
        logger.info("User with id=" + user.getId() + " added");
    }

    public void updateUserName(Long id, String name) {
        userRepo.updateUserName(id, name);
        logger.info("User with id=" + id + " changed his name");
    }

    public void removeUser(Long id) {
        userRepo.deleteById(id);
        logger.info("User with id=" + id + " deleted");
    }

    public void blockUser(Long id, Date unblockDate, Long doerId) throws NoRightException {
        if (checkRole(doerId, RoleEnum.USER)) {
            throw new NoRightException();
        }
        User user = userRepo.getById(id);
        Blocking blocking = new Blocking(user, unblockDate);
        blockingRepo.save(blocking);
        logger.info("User with id=" + id + " is blocked until" + unblockDate);
    }

    public void unblockUser(Long id, Long doerId) throws NoRightException {
        if (checkRole(doerId, RoleEnum.USER)) {
            throw new NoRightException();
        }
        User user = userRepo.getById(id);
        blockingRepo.deleteAllByUser(user);
        logger.info("User with id=" + id + " is unblocked");
    }

    public void upgradeToModerator(Long id, Long doerId) throws NoRightException {
        if (!checkRole(doerId, RoleEnum.ADMINISTRATOR)) {
            throw new NoRightException();
        }
        Role role = roleRepo.getByRole(RoleEnum.MODERATOR);
        userRepo.assignRole(id, role);
        logger.info("User with id=" + id + " now is a moderator");
    }

    public void downgradeToSimpleUser(Long id, Long doerId) throws NoRightException {
        if (!checkRole(doerId, RoleEnum.ADMINISTRATOR)) {
            throw new NoRightException();
        }
        Role role = roleRepo.getByRole(RoleEnum.USER);
        userRepo.assignRole(id, role);
        logger.info("User with id=" + id + " now is a simple user");
    }

    private boolean checkRole(Long doerId, RoleEnum role) {
        User doer = userRepo.getById(doerId);
        return doer.getRole().getRole() == role;
    }
}
