package com.sda.dao;

import com.sda.dao.UsersDAO;
import model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UsersDAOTest {
    private final UsersDAO usersDAO = new UsersDAO();

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("testusername");
        user.setPassword("testpassword");
        user.setName("testname");
        user.setSurname("testsurname");
        user.setAge(25);
        user.setEmail("testemail@test.com");

        usersDAO.addUser(user);


    }
    @Test
    public void testDeleteByUsername() {
        User user = new User();
        user.setUsername("testusername");
        user.setPassword("testpassword");
        user.setName("testname");
        user.setSurname("testsurname");
        user.setAge(25);
        user.setEmail("testemail@test.com");

        usersDAO.addUser(user);

        boolean result = usersDAO.deleteByUsername("testusername");
        assertTrue(result);


    }

    @Test
    public void testDeleteByUsername_UserNotFound() {
        boolean result = usersDAO.deleteByUsername("nonexistentusername");
        assertFalse(result);
    }
    }
