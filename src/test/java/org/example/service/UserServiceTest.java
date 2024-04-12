package org.example.service;

import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void whenUserIsExistThenAuthorizationDataIsCorrect() {
        User user = new User("user", "user", "Nick", 25);
        userService.addUser(user);
        assertTrue(userService.authorizationDataIsCorrect("user", "user"));
    }

    @Test
    void whenUserIsNotExistThenAuthorizationDataIsIncorrect() {
        assertFalse(userService.authorizationDataIsCorrect("user", "user"));
    }

    @Test
    void whenLoginIsNotExistThenRegistrationDataIsCorrect() {
        assertTrue(userService.registrationDataIsCorrect("user"));
    }

    @Test
    void whenLoginIsExistThenRegistrationDataIsIncorrect() {
        User user = new User("user", "user", "Nick", 25);
        userService.addUser(user);
        assertFalse(userService.registrationDataIsCorrect("user"));
    }
}