package org.example.output;

import org.example.model.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class AdminRepositoryTest {

    private AdminRepository adminRepository;

    @BeforeEach
    void setUp() {
        adminRepository = new AdminRepositoryImpl();
    }

    @Test
    void whenGetAdminListThenSizeIsCorrect() {
        assertEquals(1, adminRepository.getAdminList().size());
    }

    @Test
    void whenGetAdminByExistingLoginThenIsCorrect() {
        Admin admin = new Admin("admin", "admin", "Владимир", 27);
        assertEquals(admin, adminRepository.getAdmin("admin"));
    }

    @Test
    void whenGetAdminByNonExistingLoginThenThrowException() {
        assertThrows(NoSuchElementException.class, () -> adminRepository.getAdmin("incorrect"));
    }
}