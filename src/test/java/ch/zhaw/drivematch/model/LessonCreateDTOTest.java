package ch.zhaw.drivematch.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LessonCreateDTOTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        LessonCreateDTO lessonCreateDTO = new LessonCreateDTO();

        // Act
        lessonCreateDTO.setDescription("Test Description");
        lessonCreateDTO.setLessonType(LessonType.FUNDAMENTALS);
        lessonCreateDTO.setPrice(99.99);

        // Assert
        assertEquals("Test Description", lessonCreateDTO.getDescription());
        assertEquals(LessonType.FUNDAMENTALS, lessonCreateDTO.getLessonType());
        assertEquals(99.99, lessonCreateDTO.getPrice());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        LessonCreateDTO lessonCreateDTO = new LessonCreateDTO();

        // Assert
        assertNotNull(lessonCreateDTO);
        assertNull(lessonCreateDTO.getDescription());
        assertNull(lessonCreateDTO.getLessonType());
        assertNull(lessonCreateDTO.getPrice());
    }
}
