package com.example.simbirsoftbackendpractice.controller;

import com.example.simbirsoftbackendpractice.domain.Role;
import com.example.simbirsoftbackendpractice.domain.User;
import com.example.simbirsoftbackendpractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/room/assign_role/{id}")
    public String assignRole(@PathVariable Long id, @RequestBody Role role, Model model) {
        userService.assignRole(id, role);
        return "";
    }

    @DeleteMapping("/room/{id}")
    public String removeUser(@PathVariable("id") Long id, Model model) {
        userService.removeUser(id);
        return "";
    }
}
