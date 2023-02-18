package com.sda.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}