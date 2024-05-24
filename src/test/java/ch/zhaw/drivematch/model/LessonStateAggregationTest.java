package ch.zhaw.drivematch.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LessonStateAggregationTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        LessonStateAggregation lessonStateAggregation = new LessonStateAggregation();
        List<String> lessonIds = Arrays.asList("lesson1", "lesson2", "lesson3");

        // Act
        lessonStateAggregation.setId("test-id");
        lessonStateAggregation.setLessonIds(lessonIds);
        lessonStateAggregation.setCount("3");

        // Assert
        assertEquals("test-id", lessonStateAggregation.getId());
        assertEquals(lessonIds, lessonStateAggregation.getLessonIds());
        assertEquals("3", lessonStateAggregation.getCount());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        LessonStateAggregation lessonStateAggregation = new LessonStateAggregation();

        // Assert
        assertNotNull(lessonStateAggregation);
        assertNull(lessonStateAggregation.getId());
        assertNull(lessonStateAggregation.getLessonIds());
        assertNull(lessonStateAggregation.getCount());
    }
}
