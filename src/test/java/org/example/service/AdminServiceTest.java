package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminServiceTest {

    private AdminService adminService;

    @BeforeEach
    void setUp() {
        adminService = new AdminService();
    }

    @Test
    void whenAdminIsExistThenAuthorizationDataIsCorrect() {
        assertTrue(adminService.authorizationDataIsCorrect("admin", "admin"));
    }

    @Test
    void whenAdminIsNotExistThenAuthorizationDataIsIncorrect() {
        assertFalse(adminService.authorizationDataIsCorrect("123", "123"));
    }
}