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
        roomService.getRoom(id);
        return "";
    }

    @PostMapping("/room/create")
    public String createRoom(@RequestBody Room room, @RequestBody Long creatorId, Model model) {
        try {
            roomService.createRoom(room, creatorId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "";
    }

    @PutMapping("/room/{id}")
    public String updateRoomName(@PathVariable Long id,
                                 @RequestBody String name,
                                 @RequestBody Long doerId, Model model) {
        try {
            roomService.updateRoomName(id, name, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "";
    }

    @DeleteMapping("/room/{id}")
    public String removeRoom(@PathVariable("id") Long id, Model model) {
        roomService.removeRoom(id);
        return "";
    }

    @PutMapping("/room/add/{roomId}/{userId}")
    public String addUserInRoom(@PathVariable("roomId") Long roomId,
                                @PathVariable("userId") Long userId,
                                @RequestBody Long doerId, Model model) {
        try {
            roomService.addUserInRoom(roomId, userId, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "";
    }

    @PutMapping("/room/delete/{roomId}/{userId}")
    public String deleteUserFromRoom(@PathVariable("roomId") Long roomId,
                                     @PathVariable("userId") Long userId,
                                     @RequestBody Long doerId, Model model) {
        try {
            roomService.deleteUserFromRoom(roomId, userId, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "";
    }
}