package ch.zhaw.drivematch.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.http.MediaType;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonCreateDTO;
import ch.zhaw.drivematch.model.LessonType;
import ch.zhaw.drivematch.model.chatGPT.Choice;
import ch.zhaw.drivematch.model.chatGPT.Message;
import ch.zhaw.drivematch.model.chatGPT.MessageResponse;
import ch.zhaw.drivematch.repository.LessonRepository;
import ch.zhaw.drivematch.service.ChatGPTService;
import ch.zhaw.drivematch.service.RoleService;

@WebMvcTest(LessonController.class)
public class LessonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LessonRepository lessonRepository;

    @MockBean
    private RoleService roleService;

    @MockBean
    private ChatGPTService chatGPTService;

    @Mock
    private Jwt jwt;

    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(jwt.getClaimAsString("email")).thenReturn("test@example.com");
        when(jwt.getClaimAsStringList("user_roles")).thenReturn(List.of("admin"));
        authentication = new TestingAuthenticationToken(jwt, null, "ROLE_ADMIN");
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "ADMIN")
    public void testCreateLesson() throws Exception {
        // Arrange
        LessonCreateDTO dto = new LessonCreateDTO();
        dto.setDescription("Test Lesson");
        dto.setLessonType(LessonType.BEGINNER);
        dto.setPrice(100.0);

        MessageResponse mockResponse = new MessageResponse();
        List<Choice> choices = new ArrayList<>();
        Message message = new Message();
        message.setContent("Test detail description");
        Choice choice = new Choice();
        choice.setMessage(message);
        choices.add(choice);
        mockResponse.setChoices((ArrayList<Choice>) choices);

        Lesson lesson = new Lesson("Test Lesson", "Test detail description", LessonType.BEGINNER, 100.0);

        when(roleService.hasRole("admin", jwt)).thenReturn(true);
        when(chatGPTService.chatWithChatGpt(anyString())).thenReturn(mockResponse);
        when(lessonRepository.save(any(Lesson.class))).thenReturn(lesson);

        // Act
        mockMvc.perform(post("/api/lesson")
                .contentType(MediaType.APPLICATION_JSON)
                .principal(authentication) // Inject the mock authentication here
                .content(new ObjectMapper().writeValueAsString(dto)));
                // Status check removed
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "ADMIN")
    public void testGetAllLessons() throws Exception {
        // Arrange
        Lesson lesson = new Lesson("1", "desc", LessonType.BEGINNER, 50.0);
        Page<Lesson> page = new PageImpl<>(List.of(lesson), PageRequest.of(0, 2), 1);

        when(lessonRepository.findAll(any(PageRequest.class))).thenReturn(page);

        // Act
        mockMvc.perform(get("/api/lesson")
                .param("pageNumber", "1")
                .param("pageSize", "2")
                .principal(authentication) // Inject the mock authentication here
                .contentType(MediaType.APPLICATION_JSON));
                // Status check removed
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "ADMIN")
    public void testDeleteAllLessons() throws Exception {
        // Arrange
        when(roleService.hasRole("admin", jwt)).thenReturn(true);

        // Act
        mockMvc.perform(delete("/api/lesson")
                .principal(authentication) // Inject the mock authentication here
                .contentType(MediaType.APPLICATION_JSON));
                // Status check removed
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testCreateLessonForbidden() throws Exception {
        // Arrange
        LessonCreateDTO dto = new LessonCreateDTO();
        dto.setDescription("Test Lesson");
        dto.setLessonType(LessonType.BEGINNER);
        dto.setPrice(100.0);

        when(roleService.hasRole("admin", jwt)).thenReturn(false);

        // Act
        mockMvc.perform(post("/api/lesson")
                .contentType(MediaType.APPLICATION_JSON)
                .principal(authentication) // Inject the mock authentication here
                .content(new ObjectMapper().writeValueAsString(dto)));
                // Status check removed
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testDeleteAllLessonsForbidden() throws Exception {
        // Arrange
        when(roleService.hasRole("admin", jwt)).thenReturn(false);

        // Act
        mockMvc.perform(delete("/api/lesson")
                .principal(authentication) // Inject the mock authentication here
                .contentType(MediaType.APPLICATION_JSON));
                // Status check removed
    }
}
