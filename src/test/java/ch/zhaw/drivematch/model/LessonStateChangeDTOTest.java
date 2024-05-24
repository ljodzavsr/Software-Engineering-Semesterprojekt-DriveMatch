package ch.zhaw.drivematch.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LessonStateChangeDTOTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        LessonStateChangeDTO lessonStateChangeDTO = new LessonStateChangeDTO();

        // Act
        lessonStateChangeDTO.setLessonId("lesson-id");
        lessonStateChangeDTO.setInstructorEmail("instructor@example.com");

        // Assert
        assertEquals("lesson-id", lessonStateChangeDTO.getLessonId());
        assertEquals("instructor@example.com", lessonStateChangeDTO.getInstructorEmail());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        LessonStateChangeDTO lessonStateChangeDTO = new LessonStateChangeDTO();

        // Assert
        assertNotNull(lessonStateChangeDTO);
        assertNull(lessonStateChangeDTO.getLessonId());
        assertNull(lessonStateChangeDTO.getInstructorEmail());
    }
}
