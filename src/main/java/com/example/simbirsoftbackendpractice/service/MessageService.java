package com.example.simbirsoftbackendpractice.service;

import com.example.simbirsoftbackendpractice.domain.Blocking;
import com.example.simbirsoftbackendpractice.domain.Message;
import com.example.simbirsoftbackendpractice.domain.RoleEnum;
import com.example.simbirsoftbackendpractice.domain.User;
import com.example.simbirsoftbackendpractice.exception.NoRightException;
import com.example.simbirsoftbackendpractice.repository.MessageRepo;
import com.example.simbirsoftbackendpractice.repository.UserRepo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class MessageService {

    private static final Logger logger = LogManager.getLogger(MessageService.class);

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    UserRepo userRepo;

    public Message getMessage(Long id) {
        return messageRepo.getById(id);
    }

    public void addMessage(Message message, Long authorId) throws NoRightException {
        if (isBlocked(authorId)) {
            throw new NoRightException();
        }
        messageRepo.save(message);
        logger.info("Message with id=" + message.getId() + " added by user with id=" + authorId);
    }

    public void deleteMessage(Long id, Long doerId) throws NoRightException {
        if (checkRole(doerId, RoleEnum.USER) || isBlocked(doerId)) {
            throw new NoRightException();
        }
        messageRepo.deleteById(id);
        logger.info("Message with id=" + id + " deleted by user with id=" + doerId);
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
