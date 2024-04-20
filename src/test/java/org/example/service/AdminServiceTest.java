package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdminServiceTest {

    private AdminService adminService;

    @BeforeEach
    void setUp() {
        adminService = new AdminService();
    }

    @Test
    void whenAdminIsExistThenAuthorizationDataIsCorrect() {
        assertTrue(adminService.isAuthorizationDataCorrect("admin", "admin"));
    }

    @Test
    void whenAdminIsNotExistThenAuthorizationDataIsIncorrect() {
        assertFalse(adminService.isAuthorizationDataCorrect("123", "123"));
    }
}