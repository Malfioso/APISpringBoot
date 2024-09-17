package com.example.apispringboot.service;

import com.example.apispringboot.User;
import com.example.apispringboot.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        User savedUser = userService.createUser(user);

        assertNotNull(savedUser.getId());
        assertEquals("John", savedUser.getFirstName());
        assertEquals("Doe", savedUser.getLastName());
    }

    // Ajoutez d'autres méthodes de test ici

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        User savedUser = userService.createUser(user);

        savedUser.setFirstName("Jane");
        savedUser.setLastName("Doe");

        User updatedUser = userService.updateUser(savedUser.getId(), savedUser);

        assertEquals(savedUser.getId(), updatedUser.getId());
        assertEquals("Jane", updatedUser.getFirstName());
        assertEquals("Doe", updatedUser.getLastName());
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        User savedUser = userService.createUser(user);

        userService.deleteUser(savedUser.getId());

        assertNull(userService.getUserById(savedUser.getId()));
    }

    @Test
    public void testGetUserById() {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");

        User savedUser = userService.createUser(user);

        User retrievedUser = userService.getUserById(savedUser.getId());

        assertEquals(savedUser.getId(), retrievedUser.getId());
        assertEquals("John", retrievedUser.getFirstName());
        assertEquals("Doe", retrievedUser.getLastName());
    }

    @Test
    public void testGetAllUsers() {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Doe");

        userService.createUser(user1);
        userService.createUser(user2);

        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    public void testGetUserByIdNotFound() {
        assertNull(userService.getUserById(1L));
    }

    @Test
    public void testUpdateUserNotFound() {
        assertNull(userService.updateUser(1L, new User()));
    }

    @Test
    public void testDeleteUserNotFound() {
        userService.deleteUser(1L);
    }

}