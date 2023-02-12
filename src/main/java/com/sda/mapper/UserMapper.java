package com.sda.mapper;

import com.sda.dto.UserDTO;
import model.User;

public class UserMapper {

    public UserDTO map(User user) {
        return new UserDTO(user.getUsername(), user.getName(), user.getSurname(), user.getAge(), user.getEmail());
    }

}
