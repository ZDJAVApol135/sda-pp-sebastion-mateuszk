package com.sda.service;
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
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("User with username " + username + " not found");
        }
        userDAO.deleteByUsername(String.valueOf(user));
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

        userDAO.update(user);
        return userMapper.map(user);
    }
}