package com.sda.exception.dto;

import java.io.Serializable;

import com.sda.dao.UsersDAO;
import com.sda.exception.NotFoundException;
import com.sda.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.User;




public record UserDTO(String username,
                      String name,
                      String surname,
                      int age,
                      String email
                      ) {



    @Builder public UserDTO{}


}
