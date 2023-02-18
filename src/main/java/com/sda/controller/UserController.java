package com.sda.controller;

import com.sda.exception.dto.UserDTO;
import com.sda.exception.NotFoundException;
import com.sda.exception.UsernameConflictException;
import com.sda.service.UserService;
import lombok.extern.slf4j.Slf4j;
import model.User;

import java.util.List;

@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void findAll() {
        System.out.println("Users list:");
        List<UserDTO> users=userService.findAll();
        users.forEach(System.out::println);
        if (users.isEmpty()) {
            System.out.println("Users list empty!");
        }
    }
    public void findByUsername(String username) {
        try {
            UserDTO user = userService.findByUsername(username);
            System.out.println("User found: " + user);
        } catch (NotFoundException e) {
           log.error("NotFoundException: {}",e.getMessage());
        }
    }
    public void deleteByUsername(String username) {
        try {
            userService.deleteByUsername(username);
            System.out.println("User with username '" + username + "' deleted!");
        } catch (NotFoundException e) {
            log.error("User with username '" + username + "' not found!");
        } catch (Exception e) {
            log.error(("Error occurred while deleting user with username '" + username + "': "), e.getMessage());
        }
    }
    public void create(User user) {
        try {
            userService.create(user);
            System.out.println("User with username '" + user.getUsername() + "' created!");
        } catch (UsernameConflictException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }
    public void update(User user, String username) {
        try {
            userService.update(user, username);
            System.out.println("User with username '" + username + "' updated!");
            System.out.println("User after update: " + userService.findByUsername(user.getUsername()));
        } catch (NotFoundException e) {
            log.error(e.getMessage());
        } catch (UsernameConflictException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("An error occurred while updating user: ", e.getMessage());
        }
    }
}