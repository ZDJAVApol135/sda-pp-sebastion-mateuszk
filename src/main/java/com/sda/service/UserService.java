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

}