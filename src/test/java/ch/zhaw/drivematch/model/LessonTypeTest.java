package ch.zhaw.drivematch.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class LessonTypeTest {

    @Test
    public void testEnumValues() {
        // Arrange & Act
        LessonType beginner = LessonType.BEGINNER;
        LessonType intermediate = LessonType.INTERMEDIATE;
        LessonType advanced = LessonType.ADVANCED;
        LessonType fundamentals = LessonType.FUNDAMENTALS;
        LessonType urban = LessonType.URBAN;
        LessonType highway = LessonType.HIGHWAY;
        LessonType parking = LessonType.PARKING;
        LessonType refresher = LessonType.REFRESHER;

        // Assert
        assertEquals("BEGINNER", beginner.name());
        assertEquals("INTERMEDIATE", intermediate.name());
        assertEquals("ADVANCED", advanced.name());
        assertEquals("FUNDAMENTALS", fundamentals.name());
        assertEquals("URBAN", urban.name());
        assertEquals("HIGHWAY", highway.name());
        assertEquals("PARKING", parking.name());
        assertEquals("REFRESHER", refresher.name());
        
        assertSame(LessonType.BEGINNER, LessonType.valueOf("BEGINNER"));
        assertSame(LessonType.INTERMEDIATE, LessonType.valueOf("INTERMEDIATE"));
        assertSame(LessonType.ADVANCED, LessonType.valueOf("ADVANCED"));
        assertSame(LessonType.FUNDAMENTALS, LessonType.valueOf("FUNDAMENTALS"));
        assertSame(LessonType.URBAN, LessonType.valueOf("URBAN"));
        assertSame(LessonType.HIGHWAY, LessonType.valueOf("HIGHWAY"));
        assertSame(LessonType.PARKING, LessonType.valueOf("PARKING"));
        assertSame(LessonType.REFRESHER, LessonType.valueOf("REFRESHER"));
    }

    @Test
    public void testEnumValuesArray() {
        // Act
        LessonType[] types = LessonType.values();

        // Assert
        assertEquals(8, types.length);
        assertArrayEquals(new LessonType[]{
            LessonType.BEGINNER, LessonType.INTERMEDIATE, LessonType.ADVANCED, 
            LessonType.FUNDAMENTALS, LessonType.URBAN, LessonType.HIGHWAY, 
            LessonType.PARKING, LessonType.REFRESHER
        }, types);
    }
}
