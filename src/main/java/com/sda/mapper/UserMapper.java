package com.sda.mapper;

import com.sda.exception.dto.UserDTO;
import model.User;

public class UserMapper {

    public static UserDTO map(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .age(user.getAge())
                .email(user.getEmail())
                .build();
    }


}
