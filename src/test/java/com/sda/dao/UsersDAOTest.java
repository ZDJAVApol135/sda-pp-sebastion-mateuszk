package com.sda.dao;

import db.HibernateUtils;
import model.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class UsersDAOTest {
    private final UsersDAO usersDAO = new UsersDAO();

    @Test
    void AddUserTest() {
        // given
        String username = UUID.randomUUID().toString();
        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setAge(25);
        expectedUser.setName("testname");
        expectedUser.setSurname("testsurname");
        expectedUser.setPassword("testpassword!");
        expectedUser.setEmail("testemail@test.com");

        // when
        usersDAO.addUser(expectedUser);

        // then
        Session session = HibernateUtils.openSession();
        User actualUser = session.find(User.class, username);
        session.close();

        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);
        Assertions.assertEquals(expectedUser.getName(), actualUser.getName());
        Assertions.assertEquals(expectedUser.getSurname(), actualUser.getSurname());
        Assertions.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        Assertions.assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        Assertions.assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        Assertions.assertEquals(expectedUser.getAge(), actualUser.getAge());


    }
    /*@Test
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
    }*/
}
