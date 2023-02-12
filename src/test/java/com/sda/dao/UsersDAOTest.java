package com.sda.dao;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
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
        User expectedUser = createUser(username);

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
        String username = UUID.randomUUID().toString();
        User user = createUser(username);

        usersDAO.addUser(user);

        boolean result = usersDAO.deleteByUsername(username);
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
        User user1 = createUser("userName");
        User user2 = createUser("userName2");

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
        String username = UUID.randomUUID().toString();
        User user = createUser(username);

        usersDAO.addUser(user);

        // When
        User foundUser = usersDAO.findByUsername(username);

        // Then
        assertEquals(user, foundUser);
    }

    @Test
    public void testFindByUsernameNotFound() {
        // Given
        String nonexistent = "nonexistent";

        // When
        User foundUser = usersDAO.findByUsername(nonexistent);

        // Then
        assertNull(foundUser);
    }

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


