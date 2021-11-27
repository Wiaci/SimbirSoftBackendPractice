package com.example.simbirsoftbackendpractice.controller;

import com.example.simbirsoftbackendpractice.exception.NoRightException;
import com.example.simbirsoftbackendpractice.service.ChatBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatBotController {

    @Autowired
    private ChatBotService chatBotService;

    @PostMapping
    public String executeCommand(@RequestParam Long user_id, @RequestParam String command, Model model) {
        String output = null;
        try {
            output = chatBotService.executeCommand(command, user_id);
            model.addAttribute("output", output);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "index";
    }

}
