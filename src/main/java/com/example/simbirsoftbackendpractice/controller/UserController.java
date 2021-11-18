package com.example.simbirsoftbackendpractice.controller;

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
        model.addAttribute("name", user.getName());
        model.addAttribute("role", user.getRole().getRole());
        return "index";
    }

    @PostMapping("/room/add")
    public String addUser(@RequestParam User user, Model model) {
        userService.addUser(user);
        return "index";
    }

    @PutMapping("/room/change_name/{id}")
    public String updateUserName(@PathVariable Long id,
                                 @RequestParam String name, Model model) {
        userService.updateUserName(id, name);
        return "index";
    }

    @PutMapping("/room/upgradeToModerator/{id}")
    public String upgradeToModerator(@PathVariable Long id,
                                     @RequestParam Long doerId, Model model) {
        try {
            userService.upgradeToModerator(id, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "index";
    }

    @PutMapping("/room/downgradeToSimpleUser/{id}")
    public String downgradeToSimpleUser(@PathVariable Long id,
                                        @RequestParam Long doerId, Model model) {
        try {
            userService.downgradeToSimpleUser(id, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "index";
    }

    @DeleteMapping("/room/{id}")
    public String removeUser(@PathVariable("id") Long id, Model model) {
        userService.removeUser(id);
        return "index";
    }

    @PutMapping("/room/block/{id}")
    public String blockUser(@PathVariable("id") Long id, @RequestParam Date unblockDate,
                            @RequestParam Long doerId, Model model) {
        try {
            userService.blockUser(id, unblockDate, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "index";
    }

    @PutMapping("/room/unblock/{id}")
    public String unblockUser(@PathVariable("id") Long id,
                              @RequestParam Long doerId, Model model) {
        try {
            userService.unblockUser(id, doerId);
        } catch (NoRightException e) {
            System.out.println("There will be an error handle");
        }
        return "index";
    }
}
