package ch.zhaw.drivematch.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ch.zhaw.drivematch.model.Instructor;
import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonState;
import ch.zhaw.drivematch.model.LessonType;
import ch.zhaw.drivematch.repository.InstructorRepository;
import ch.zhaw.drivematch.repository.LessonRepository;

public class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private LessonService lessonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAssignLesson() {
        // Arrange
        String lessonId = "lesson123";
        String instructorEmail = "instructor@example.com";
        Lesson lesson = new Lesson("Test Description", "Detail Description", LessonType.FUNDAMENTALS, 100.0);
        lesson.setId(lessonId);
        lesson.setLessonState(LessonState.NEW);
        Instructor instructor = new Instructor(instructorEmail, "John", "Doe");
        instructor.setId("instructor123");

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));
        when(instructorRepository.findFirstByEmail(instructorEmail)).thenReturn(instructor);

        // Act
        Optional<Lesson> result = lessonService.assignLesson(lessonId, instructorEmail);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(LessonState.ASSIGNED, result.get().getLessonState());
        assertEquals("instructor123", result.get().getInstructorId());
        verify(lessonRepository, times(1)).save(lesson);
    }

    @Test
    public void testCompleteLesson() {
        // Arrange
        String lessonId = "lesson123";
        String instructorEmail = "instructor@example.com";
        Lesson lesson = new Lesson("Test Description", "Detail Description", LessonType.FUNDAMENTALS, 100.0);
        lesson.setId(lessonId);
        lesson.setLessonState(LessonState.ASSIGNED);
        lesson.setInstructorId("instructor123");
        Instructor instructor = new Instructor(instructorEmail, "John", "Doe");
        instructor.setId("instructor123");

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.of(lesson));
        when(instructorRepository.findFirstByEmail(instructorEmail)).thenReturn(instructor);

        // Act
        Optional<Lesson> result = lessonService.completeLesson(lessonId, instructorEmail);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(LessonState.DONE, result.get().getLessonState());
        verify(lessonRepository, times(1)).save(lesson);
    }

    @Test
    public void testAssignLessonLessonNotFound() {
        // Arrange
        String lessonId = "lesson123";
        String instructorEmail = "instructor@example.com";

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.empty());

        // Act
        Optional<Lesson> result = lessonService.assignLesson(lessonId, instructorEmail);

        // Assert
        assertFalse(result.isPresent());
        verify(lessonRepository, never()).save(any(Lesson.class));
    }

    @Test
    public void testCompleteLessonLessonNotFound() {
        // Arrange
        String lessonId = "lesson123";
        String instructorEmail = "instructor@example.com";

        when(lessonRepository.findById(lessonId)).thenReturn(Optional.empty());

        // Act
        Optional<Lesson> result = lessonService.completeLesson(lessonId, instructorEmail);

        // Assert
        assertFalse(result.isPresent());
        verify(lessonRepository, never()).save(any(Lesson.class));
    }
}
