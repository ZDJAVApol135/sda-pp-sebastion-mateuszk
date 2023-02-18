package com.sda.dao;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.sda.dto.UserDTO;
import db.HibernateUtils;
import model.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private User createUser(String userName) {
        Faker faker = new Faker();
        Name name = faker.name();

        User user = new User();
        user.setUsername(userName);
        user.setPassword(faker.internet().password());
        user.setName(name.firstName());
        user.setSurname(name.lastName());
        user.setAge(faker.number().numberBetween(0, 150));
        user.setEmail(faker.internet().emailAddress());
        return user;
    }


}
