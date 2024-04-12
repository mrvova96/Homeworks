package org.example.output;

import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl();
    }

    @Test
    void whenAddNewUserThenSizeIsChanging() {
        User user = new User("123", "123", "Nick", 25);
        userRepository.addUser(user);
        assertEquals(1, userRepository.getUserList().size());
    }

    @Test
    void whenGetUserByExistingLoginThenIsCorrect() {
        User user = new User("123", "123", "Nick", 25);
        userRepository.addUser(user);
        assertEquals(user, userRepository.getUser("123"));
    }

    @Test
    void whenGetUserByNonExistingLoginThenThrowException() {
        assertThrows(NoSuchElementException.class, () -> userRepository.getUser("incorrect"));
    }
}