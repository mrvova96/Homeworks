package org.example.output;

import org.example.model.Admin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AdminRepositoryTest {

    private AdminRepository adminRepository;

    @BeforeEach
    void setUp() {
        adminRepository = new AdminRepositoryImpl();
    }

    @Test
    void whenGetAdminMapThenSizeIsCorrect() {
        assertEquals(1, adminRepository.getAdminMap().size());
    }

    @Test
    void whenGetAdminByExistingLoginThenIsCorrect() {
        Admin admin = new Admin("admin", "admin", "Владимир", 27);
        assertEquals(admin, adminRepository.getAdmin("admin"));
    }

    @Test
    void whenGetAdminByNonExistingLoginThenReturnNull() {
        assertNull(adminRepository.getAdmin("incorrect"));
    }
}