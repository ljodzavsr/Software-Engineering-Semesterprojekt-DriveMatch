package ch.zhaw.drivematch.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import ch.zhaw.drivematch.model.Instructor;
import ch.zhaw.drivematch.repository.InstructorRepository;

public class UserValidatorTest {

    @Mock
    private InstructorRepository instructorRepository;

    private UserValidator userValidator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userValidator = new UserValidator(instructorRepository);
    }

    @Test
    public void testValidate_ValidTokenWithExistingUser() {
        // Arrange
        String email = "test@example.com";
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        Jwt jwt = mock(Jwt.class);
        when(jwt.getClaimAsString("email")).thenReturn(email);
        when(instructorRepository.findFirstByEmail(email)).thenReturn(new Instructor(email, "John", "Doe"));

        // Act
        OAuth2TokenValidatorResult result = userValidator.validate(jwt);

        // Assert
        assertTrue(result.hasErrors() == false);
        verify(instructorRepository, never()).save(any());
    }

    @Test
    public void testValidate_ValidTokenWithNewUser() {
        // Arrange
        String email = "newuser@example.com";
        String givenName = "Jane";
        String familyName = "Doe";
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("given_name", givenName);
        claims.put("family_name", familyName);
        Jwt jwt = mock(Jwt.class);
        when(jwt.getClaimAsString("email")).thenReturn(email);
        when(jwt.getClaimAsString("given_name")).thenReturn(givenName);
        when(jwt.getClaimAsString("family_name")).thenReturn(familyName);
        when(instructorRepository.findFirstByEmail(email)).thenReturn(null);

        // Act
        OAuth2TokenValidatorResult result = userValidator.validate(jwt);

        // Assert
        assertTrue(result.hasErrors() == false);
        verify(instructorRepository, times(1)).save(any(Instructor.class));
    }

    @Test
    public void testValidate_InvalidToken() {
        // Arrange
        Map<String, Object> claims = new HashMap<>();
        Jwt jwt = mock(Jwt.class);
        when(jwt.getClaimAsString("email")).thenReturn(null);

        // Act
        OAuth2TokenValidatorResult result = userValidator.validate(jwt);

        // Assert
        assertTrue(result.hasErrors());
        OAuth2Error error = result.getErrors().iterator().next();
        assertEquals("invalid_token", error.getErrorCode());
        assertEquals("The required email is missing", error.getDescription());
    }
}
