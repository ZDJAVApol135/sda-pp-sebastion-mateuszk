package com.sda.service;
import java.util.List;
import java.util.stream.Collectors;


import com.sda.dao.UsersDAO;
import com.sda.dto.UserDTO;
import com.sda.exception.NotFoundException;
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
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("User with username " + username + " not found");
        }
        userDAO.deleteByUsername(String.valueOf(user));
    }
}