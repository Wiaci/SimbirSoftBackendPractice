package com.example.simbirsoftbackendpractice.service;

import com.example.simbirsoftbackendpractice.domain.Message;
import com.example.simbirsoftbackendpractice.repository.MessageRepo;
import lombok.Lombok;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class MessageService {

    @Autowired
    private MessageRepo messageRepo;

    public Message getMessage(Long id) {
        return messageRepo.getById(id);
    }

    public void addMessage(Message message) {
        messageRepo.save(message);
    }

    public void deleteMessage(Long id) {
        messageRepo.deleteById(id);
    }
}
