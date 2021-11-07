package com.example.simbirsoftbackendpractice.controller;

import com.example.simbirsoftbackendpractice.domain.Room;
import com.example.simbirsoftbackendpractice.service.MessageService;
import com.example.simbirsoftbackendpractice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/room/{id}")
    public String getRoom(@PathVariable("id") Long id, Model model) {
        roomService.getRoom(id);
        return "";
    }

    @PostMapping("/room/create")
    public String createRoom(@RequestBody Room room, Model model) {
        roomService.createRoom(room);
        return "";
    }

    @PutMapping("/room/{id}")
    public String updateRoomName(@PathVariable Long id, @RequestBody String name, Model model) {
        roomService.updateRoomName(id, name);
        return "";
    }

    @DeleteMapping("/room/{id}")
    public String removeRoom(@PathVariable("id") Long id, Model model) {
        roomService.removeRoom(id);
        return "";
    }
}
