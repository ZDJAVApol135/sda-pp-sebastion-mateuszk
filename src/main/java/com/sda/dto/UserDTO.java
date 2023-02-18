package com.sda.dto;

import java.io.Serializable;

import com.sda.dao.UsersDAO;
import com.sda.exception.NotFoundException;
import com.sda.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public record UserDTO(String username,
                      String name,
                      String surname,
                      int age,
                      String email
                      ) {


    @Builder public UserDTO{}

    public UserDTO findByUsername(String username) {
        User user = UsersDAO.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("User not found for username: " + username);
        }
        return UserMapper.map(user);
    }
    public void deleteByUsername(String username) {
        User user = UsersDAO.findByUsername(username);
        if (user == null) {
            throw new NotFoundException("User with username " + username + " not found");
        }
        UsersDAO.deleteByUsername(username);
    }
}
