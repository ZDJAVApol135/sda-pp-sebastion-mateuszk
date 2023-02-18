package com.sda.service;

import com.sda.dao.UsersDAO;
import com.sda.exception.dto.UserDTO;
import com.sda.exception.NotFoundException;
import com.sda.exception.UsernameConflictException;
import com.sda.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import model.User;

import java.util.List;


@RequiredArgsConstructor
public class UserService {

    private final UsersDAO usersDAO;
    private final UserMapper userMapper;

    public List<UserDTO> findAll() {
//        List<User> users = usersDAO.findAll();

//        List<UserDTO> userDTOS = new ArrayList<>();
//
//        for (User user : users) {
//            UserDTO dto = userMapper.map(user);
//            userDTOS.add(dto);
//        }
//        return userDTOS;

//        return usersDAO.findAll().stream()
//                .map(user -> userMapper.map(user))
//                .toList();
//
//        Function<User, UserDTO> mapFunction = new Function<>() {
//
//            @Override
//            public UserDTO apply(User user) {
//                return userMapper.map(user);
//            }
//        };
//
//        Function<User, UserDTO> lambdaFunction = userMapper::map;

        return usersDAO.findAll().stream()
                .map(user -> userMapper.map(user))
                .toList();
    }

    public UserDTO findByUsername(String username) {

        User user = usersDAO.findByUsername(username);
        throwNotFoundExceptionIfTrue(username, user == null);
        return userMapper.map(user);
    }

    public void deleteByUsername(String username) {
        boolean deleted = usersDAO.deleteByUsername(username);
        throwNotFoundExceptionIfTrue(username, !deleted);
    }

    public void create(User user) {
        boolean exists = usersDAO.exist(user.getUsername());
        if (exists) {
            String message = "User: '%s' already exists".formatted(user);
            throw new UsernameConflictException(message);
        }
        usersDAO.addUser(user);
    }

    public UserDTO update(User user, String username) {
        if (!user.getUsername().equals(username)) {
            throw new UsernameConflictException("Usernames dose not match!");
        }
        boolean exists = usersDAO.exist(username);
        throwNotFoundExceptionIfTrue(username, !exists);

        User updatedUser = usersDAO.update(user);
        return userMapper.map(updatedUser);
    }

    private void throwNotFoundExceptionIfTrue(String username, boolean condition) {
        if (condition) {
            String message = "User with username: '%s' not found".formatted(username);
            throw new NotFoundException(message);
        }
    }
}
/*
Lub
import java.util.List;
import java.util.stream.Collectors;


import com.sda.dao.UsersDAO;
import com.sda.dto.UserDTO;
import com.sda.exception.NotFoundException;
import com.sda.exception.UsernameConflictException;
import com.sda.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import model.User;
@RequiredArgsConstructor
public class UserService {
    private final UsersDAO userDAO;
    private final UserMapper userMapper;


    public List<UserDTO> findAll(){
        return userDAO.findAll().stream()
                .map(user->userMapper.map(user))
                .toList();

    }
    public UserDTO findByUsername(String username) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("User not found for username: " + username);
        }
        return UserMapper.map(user);
    }
    public void deleteByUsername(String username) {
        boolean deleted=userDAO.deleteByUsername(username);
        if (!deleted) {
            throw new NotFoundException("User with username " + username + " not found");
        }

    }

    public void create(User user) {
        if(userDAO.exist(user.getUsername())) {
            throw new UsernameConflictException("User with username " + user.getUsername() + " already exists");
        }
        userDAO.addUser(user);
    }
    public UserDTO update(User user, String username) {
        if (!username.equals(user.getUsername())) {
            throw new UsernameConflictException("Username in URL and in user object must be the same.");
        }

        if (!userDAO.exist(username)) {
            throw new NotFoundException("User with username '" + username + "' not found.");
        }



        User update=userDAO.update(user);
        return userMapper.map(update);
    }
}*/
