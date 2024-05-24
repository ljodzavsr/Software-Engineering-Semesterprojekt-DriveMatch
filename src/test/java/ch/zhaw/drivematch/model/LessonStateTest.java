package ch.zhaw.drivematch.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LessonStateTest {

    @Test
    public void testEnumValues() {
        // Arrange & Act
        LessonState newState = LessonState.NEW;
        LessonState assignedState = LessonState.ASSIGNED;
        LessonState doneState = LessonState.DONE;

        // Assert
        assertEquals("NEW", newState.name());
        assertEquals("ASSIGNED", assignedState.name());
        assertEquals("DONE", doneState.name());
        
        assertSame(LessonState.NEW, LessonState.valueOf("NEW"));
        assertSame(LessonState.ASSIGNED, LessonState.valueOf("ASSIGNED"));
        assertSame(LessonState.DONE, LessonState.valueOf("DONE"));
    }

    @Test
    public void testEnumValuesArray() {
        // Act
        LessonState[] states = LessonState.values();

        // Assert
        assertEquals(3, states.length);
        assertArrayEquals(new LessonState[]{LessonState.NEW, LessonState.ASSIGNED, LessonState.DONE}, states);
    }
}
