package com.example.simbirsoftbackendpractice.controller;

import com.example.simbirsoftbackendpractice.domain.Role;
import com.example.simbirsoftbackendpractice.domain.User;
import com.example.simbirsoftbackendpractice.exception.NoRightException;
import com.example.simbirsoftbackendpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/room/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getUser(id);
        return "";
    }

    @PostMapping("/room/add")
    public String addUser(@RequestBody User user, Model model) {
        userService.addUser(user);
        return "";
    }

    @PutMapping("/room/change_name/{id}")
    public String updateUserName(@PathVariable Long id, @RequestBody String name, Model model) {
        userService.updateUserName(id, name);
        return "";
    }

    @PutMapping("/room/upgradeToModerator/{id}")
    public String upgradeToModerator(@PathVariable Long id, @RequestBody Long doerId, Model model) {
        try {
            userService.upgradeToModerator(id, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "";
    }

    @PutMapping("/room/downgradeToSimpleUser/{id}")
    public String downgradeToSimpleUser(@PathVariable Long id, @RequestBody Long doerId, Model model) {
        try {
            userService.downgradeToSimpleUser(id, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "";
    }

    @DeleteMapping("/room/{id}")
    public String removeUser(@PathVariable("id") Long id, Model model) {
        userService.removeUser(id);
        return "";
    }

    @PutMapping("/room/block/{id}")
    public String blockUser(@PathVariable("id") Long id, @RequestBody Date unblockDate,
                            @RequestBody Long doerId, Model model) {
        try {
            userService.blockUser(id, unblockDate, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "";
    }

    @PutMapping("/room/unblock/{id}")
    public String unblockUser(@PathVariable("id") Long id, @RequestBody Long doerId, Model model) {
        try {
            userService.unblockUser(id, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "";
    }
}
