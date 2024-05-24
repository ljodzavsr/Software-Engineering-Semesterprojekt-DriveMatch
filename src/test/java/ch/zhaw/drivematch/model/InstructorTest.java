package ch.zhaw.drivematch.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InstructorTest {

    @Test
    public void testRequiredArgsConstructor() {
        // Arrange & Act
        Instructor instructor = new Instructor("test@example.com", "John", "Doe");

        // Assert
        assertEquals("test@example.com", instructor.getEmail());
        assertEquals("John", instructor.getName());
        assertEquals("Doe", instructor.getLastname());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        Instructor instructor = new Instructor();

        // Assert
        assertNotNull(instructor);
        assertNull(instructor.getId());
        assertNull(instructor.getEmail());
        assertNull(instructor.getName());
        assertNull(instructor.getLastname());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        Instructor instructor = new Instructor("test@example.com", "John", "Doe");

        // Act
        // Direct field access is needed because Lombok doesn't generate a setter for the id field.
        instructor.setId("1234");  // Direct field access
        instructor.setEmail("new@example.com");
        instructor.setName("Jane");
        instructor.setLastname("Smith");

        // Assert
        assertEquals("1234", instructor.getId());
        assertEquals("new@example.com", instructor.getEmail());
        assertEquals("Jane", instructor.getName());
        assertEquals("Smith", instructor.getLastname());
    }
}
