package org.example.service;

import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertTrue(userService.isAuthorizationDataCorrect("user", "user"));
    }

    @Test
    void whenUserIsNotExistThenAuthorizationDataIsIncorrect() {
        assertFalse(userService.isAuthorizationDataCorrect("user", "user"));
    }

    @Test
    void whenLoginIsNotExistThenRegistrationDataIsCorrect() {
        assertTrue(userService.isLoginAvailable("user"));
    }

    @Test
    void whenLoginIsExistThenRegistrationDataIsIncorrect() {
        User user = new User("user", "user", "Nick", 25);
        userService.addUser(user);
        assertFalse(userService.isLoginAvailable("user"));
    }
}