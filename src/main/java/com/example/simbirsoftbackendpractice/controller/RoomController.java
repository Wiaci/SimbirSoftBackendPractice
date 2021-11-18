package com.example.simbirsoftbackendpractice.controller;

import com.example.simbirsoftbackendpractice.domain.Room;
import com.example.simbirsoftbackendpractice.exception.NoRightException;
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
        Room room = roomService.getRoom(id);
        model.addAttribute("name", room.getName());
        model.addAttribute("roomType", room.getRoomType().getName());
        return "index";
    }

    @PostMapping("/room/create")
    public String createRoom(@RequestParam Room room, @RequestParam Long creatorId, Model model) {
        try {
            roomService.createRoom(room, creatorId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "index";
    }

    @PutMapping("/room/{id}")
    public String updateRoomName(@PathVariable Long id,
                                 @RequestParam String name,
                                 @RequestParam Long doerId, Model model) {
        try {
            roomService.updateRoomName(id, name, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "index";
    }

    @DeleteMapping("/room/{id}")
    public String removeRoom(@PathVariable("id") Long id, Model model) {
        roomService.removeRoom(id);
        return "index";
    }

    @PutMapping("/room/add/{roomId}/{userId}")
    public String addUserInRoom(@PathVariable("roomId") Long roomId,
                                @PathVariable("userId") Long userId,
                                @RequestParam Long doerId, Model model) {
        try {
            roomService.addUserInRoom(roomId, userId, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "index";
    }

    @PutMapping("/room/delete/{roomId}/{userId}")
    public String deleteUserFromRoom(@PathVariable("roomId") Long roomId,
                                     @PathVariable("userId") Long userId,
                                     @RequestParam Long doerId, Model model) {
        try {
            roomService.deleteUserFromRoom(roomId, userId, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "index";
    }
}