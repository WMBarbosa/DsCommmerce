package com.barbosa.dscommerse.service;

import com.barbosa.dscommerse.entities.User;
import com.barbosa.dscommerse.service.serviceException.ForbiddenException;
import com.barbosa.dscommerse.factories.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthService authService;

    private User clientUser;
    private User adminUser;

    @BeforeEach
    void setUp() {
        clientUser = Factory.createUser();
        adminUser = Factory.createAdminUser();
    }

    @Test
    void validateSelfOrAdminShouldDoNothingWhenUserIsAdmin() {
        when(userService.authenticate()).thenReturn(adminUser);

        assertDoesNotThrow(() -> authService.validateSelfOrAdmin(1L));
        assertDoesNotThrow(() -> authService.validateSelfOrAdmin(2L));
        verify(userService, times(2)).authenticate();
    }

    @Test
    void validateSelfOrAdminShouldDoNothingWhenUserIsSelf() {
        when(userService.authenticate()).thenReturn(clientUser);

        assertDoesNotThrow(() -> authService.validateSelfOrAdmin(1L));
        verify(userService).authenticate();
    }

    @Test
    void validateSelfOrAdminShouldThrowForbiddenExceptionWhenUserIsNotAdminNorSelf() {
        when(userService.authenticate()).thenReturn(clientUser);

        assertThrows(ForbiddenException.class, () -> authService.validateSelfOrAdmin(2L));
        verify(userService).authenticate();
    }
}
