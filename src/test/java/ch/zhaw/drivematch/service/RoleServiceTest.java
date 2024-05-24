package ch.zhaw.drivematch.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.jwt.Jwt;

public class RoleServiceTest {

    @InjectMocks
    private RoleService roleService;

    @Mock
    private Jwt jwt;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHasRole_UserHasRole() {
        // Arrange
        String role = "admin";
        when(jwt.getClaimAsStringList("user_roles")).thenReturn(List.of("admin", "user"));

        // Act
        boolean result = roleService.hasRole(role, jwt);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testHasRole_UserDoesNotHaveRole() {
        // Arrange
        String role = "admin";
        when(jwt.getClaimAsStringList("user_roles")).thenReturn(List.of("user"));

        // Act
        boolean result = roleService.hasRole(role, jwt);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testHasRole_EmptyRoles() {
        // Arrange
        String role = "admin";
        when(jwt.getClaimAsStringList("user_roles")).thenReturn(List.of());

        // Act
        boolean result = roleService.hasRole(role, jwt);

        // Assert
        assertFalse(result);
    }
}
