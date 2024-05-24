package ch.zhaw.drivematch.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LessonTest {

    @Test
    public void testRequiredArgsConstructor() {
        // Arrange & Act
        Lesson lesson = new Lesson("Description", "Detail Description", LessonType.FUNDAMENTALS, 100.0);

        // Assert
        assertEquals("Description", lesson.getDescription());
        assertEquals("Detail Description", lesson.getDetailDescription());
        assertEquals(LessonType.FUNDAMENTALS, lesson.getLessonType());
        assertEquals(100.0, lesson.getPrice());
        assertEquals(LessonState.NEW, lesson.getLessonState());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        Lesson lesson = new Lesson();

        // Assert
        assertNotNull(lesson);
        assertNull(lesson.getId());
        assertNull(lesson.getDescription());
        assertNull(lesson.getDetailDescription());
        assertNull(lesson.getLessonType());
        assertNull(lesson.getPrice());
        assertEquals(LessonState.NEW, lesson.getLessonState());
        assertNull(lesson.getInstructorId());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        Lesson lesson = new Lesson("Description", "Detail Description", LessonType.FUNDAMENTALS, 100.0);

        // Act
        lesson.setId("1234");
        lesson.setInstructorId("instructor-123");

        // Assert
        assertEquals("1234", lesson.getId());
        assertEquals("instructor-123", lesson.getInstructorId());
        assertEquals("Description", lesson.getDescription());
        assertEquals("Detail Description", lesson.getDetailDescription());
        assertEquals(LessonType.FUNDAMENTALS, lesson.getLessonType());
        assertEquals(100.0, lesson.getPrice());
        assertEquals(LessonState.NEW, lesson.getLessonState());
    }
}
