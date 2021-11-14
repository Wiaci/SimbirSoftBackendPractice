package com.example.simbirsoftbackendpractice.controller;

import com.example.simbirsoftbackendpractice.domain.Message;
import com.example.simbirsoftbackendpractice.exception.NoRightException;
import com.example.simbirsoftbackendpractice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/message/{id}")
    public String getMessage(@PathVariable("id") Long id, Model model) {
        Message message = messageService.getMessage(id);
        return "";
    }

    @PostMapping("/message/new")
    public String addMessage(@RequestBody Message message, @RequestBody Long authorId, Model model) {
        try {
            messageService.addMessage(message, authorId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "";
    }

    @DeleteMapping("/message/{id}")
    public String deleteMessage(@PathVariable("id") Long id, @RequestBody Long doerId, Model model) {
        try {
            messageService.deleteMessage(id, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "";
    }
}
