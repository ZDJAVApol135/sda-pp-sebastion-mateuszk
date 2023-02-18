package com.sda.controller;

import com.sda.dto.UserDTO;
import com.sda.exception.NotFoundException;
import com.sda.service.UserService;

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void findAll() {
        System.out.println("Users list:");
        userService.findAll().forEach(System.out::println);
        if (userService.findAll().isEmpty()) {
            System.out.println("Users list empty!");
        }
    }
    public void findByUsername(String username) {
        try {
            UserDTO user = userService.findByUsername(username);
            System.out.println("User found: " + user);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}