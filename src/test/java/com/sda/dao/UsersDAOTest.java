package com.sda.dao;

import db.HibernateUtils;
import model.User;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


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
        assertEquals(expectedUser, actualUser);
        assertEquals(expectedUser.getName(), actualUser.getName());
        assertEquals(expectedUser.getSurname(), actualUser.getSurname());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getPassword(), actualUser.getPassword());
        assertEquals(expectedUser.getAge(), actualUser.getAge());


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

    @Test
    public void testFindAll() {
        // Given
        User user1 = new User();
        user1.setUsername("testusername");
        user1.setPassword("testpassword");
        user1.setName("testname");
        user1.setSurname("testsurname");
        user1.setAge(25);
        user1.setEmail("testemail@test.com");
        User user2 = new User();
        user2.setUsername("testusernamee");
        user2.setPassword("testpasswordd");
        user2.setName("testnamee");
        user2.setSurname("testsurnamee");
        user2.setAge(26);
        user2.setEmail("testemail@testt.com");
        usersDAO.addUser(user1);
        usersDAO.addUser(user2);

        // When
        List<User> users = usersDAO.findAll();

        // Then
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    public void testFindByUsername() {
        // Given
        User user = new User();
        user.setUsername("testusername");
        user.setPassword("testpassword");
        user.setName("testname");
        user.setSurname("testsurname");
        user.setAge(25);
        user.setEmail("testemail@test.com");
        usersDAO.addUser(user);

        // When
        User foundUser = usersDAO.findByUsername("testusername");

        // Then
        assertEquals(user, foundUser);
    }

    @Test
    public void testFindByUsernameNotFound() {
        // Given

        // When
        User foundUser = usersDAO.findByUsername("nonexistent");

        // Then
        assertNull(foundUser);
    }
}
