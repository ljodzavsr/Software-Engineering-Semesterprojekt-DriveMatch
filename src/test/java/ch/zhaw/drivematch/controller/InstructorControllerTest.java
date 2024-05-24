package ch.zhaw.drivematch.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ch.zhaw.drivematch.model.Instructor;
import ch.zhaw.drivematch.repository.InstructorRepository;
import ch.zhaw.drivematch.service.MailValidatorService;
import ch.zhaw.drivematch.service.RoleService;

@WebMvcTest(InstructorController.class)
public class InstructorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InstructorRepository instructorRepository;

    @MockBean
    private RoleService roleService;

    @MockBean
    private MailValidatorService mailValidatorService;

    @Mock
    private Jwt jwt;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(jwt.getClaimAsString("email")).thenReturn("test@example.com");
        when(authentication.getPrincipal()).thenReturn(jwt);
        when(authentication.isAuthenticated()).thenReturn(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetAllInstructor() throws Exception {
        // Arrange
        Instructor instructor = new Instructor("test@example.com", "John", "Doe");
        Page<Instructor> page = new PageImpl<>(List.of(instructor), PageRequest.of(0, 2), 1);
        when(instructorRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(roleService.hasRole(anyString(), any(Jwt.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(get("/api/instructor")
                .param("pageNumber", "1")
                .param("pageSize", "2")
                .principal(authentication)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].email").value("test@example.com"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetInstructorById() throws Exception {
        // Arrange
        Instructor instructor = new Instructor("test@example.com", "John", "Doe");
        when(instructorRepository.findById(anyString())).thenReturn(Optional.of(instructor));
        when(roleService.hasRole(anyString(), any(Jwt.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(get("/api/instructor/{id}", "1")
                .principal(authentication)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetInstructorById_NotFound() throws Exception {
        // Arrange
        when(instructorRepository.findById(anyString())).thenReturn(Optional.empty());
        when(roleService.hasRole(anyString(), any(Jwt.class))).thenReturn(true);

        // Act & Assert
        mockMvc.perform(get("/api/instructor/{id}", "1")
                .principal(authentication)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetMyInstructorId() throws Exception {
        // Arrange
        Instructor instructor = new Instructor("test@example.com", "John", "Doe");
        when(instructorRepository.findFirstByEmail(anyString())).thenReturn(instructor);

        // Act & Assert
        mockMvc.perform(get("/api/me/instructor")
                .principal(authentication)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetAllInstructor_Forbidden() throws Exception {
        // Arrange
        when(roleService.hasRole(anyString(), any(Jwt.class))).thenReturn(false);

        // Act & Assert
        mockMvc.perform(get("/api/instructor")
                .param("pageNumber", "1")
                .param("pageSize", "2")
                .principal(authentication)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
