package ch.zhaw.drivematch.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonState;
import ch.zhaw.drivematch.model.LessonType;
import ch.zhaw.drivematch.service.LessonService;
import ch.zhaw.drivematch.service.MailService;
import ch.zhaw.drivematch.service.RoleService;

@WebMvcTest(ServiceController.class)
public class ServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LessonService lessonService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private MailService mailService;

    @Mock
    private Jwt jwt;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(jwt.getClaimAsString("email")).thenReturn("test@example.com");
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testAssignToMe() throws Exception {
        // Arrange
        Lesson lesson = new Lesson("1", "desc", LessonType.BEGINNER, 50.0);
        lesson.setLessonState(LessonState.NEW);
        when(lessonService.assignLesson(anyString(), anyString())).thenReturn(Optional.of(lesson));

        // Act
        mockMvc.perform(put("/api/service/me/assignlesson")
                .param("lessonId", "1")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testAssignToMe_BadRequest() throws Exception {
        // Arrange
        when(lessonService.assignLesson(anyString(), anyString())).thenReturn(Optional.empty());

        // Act
        mockMvc.perform(put("/api/service/me/assignlesson")
                .param("lessonId", "1")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testCompleteMyLesson() throws Exception {
        // Arrange
        Lesson lesson = new Lesson("1", "desc", LessonType.BEGINNER, 50.0);
        lesson.setLessonState(LessonState.DONE);
        when(lessonService.completeLesson(anyString(), anyString())).thenReturn(Optional.of(lesson));

        // Act
        mockMvc.perform(put("/api/service/me/completelesson")
                .param("lessonId", "1")
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "test@example.com", roles = "USER")
    public void testCompleteMyLesson_BadRequest() throws Exception {
        // Arrange
        when(lessonService.completeLesson(anyString(), anyString())).thenReturn(Optional.empty());

        // Act
        mockMvc.perform(put("/api/service/me/completelesson")
                .param("lessonId", "1")
                .contentType(MediaType.APPLICATION_JSON));
    }
}