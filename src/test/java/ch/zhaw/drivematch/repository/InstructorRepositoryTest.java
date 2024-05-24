package ch.zhaw.drivematch.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import ch.zhaw.drivematch.model.Instructor;

@DataMongoTest
public class InstructorRepositoryTest {

    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    public void testFindFirstByEmail() {
        // Arrange
        String email = "test@example.com";
        Instructor instructor = new Instructor(email, "John", "Doe");
        instructorRepository.save(instructor);

        // Act
        Instructor foundInstructor = instructorRepository.findFirstByEmail(email);

        // Assert
        assertNotNull(foundInstructor);
        assertEquals(email, foundInstructor.getEmail());
    }

    @Test
    public void testSaveInstructor() {
        // Arrange
        String email = "new@example.com";
        Instructor instructor = new Instructor(email, "Jane", "Doe");

        // Act
        Instructor savedInstructor = instructorRepository.save(instructor);

        // Assert
        assertNotNull(savedInstructor.getId()); // Überprüfen Sie, ob die ID nach dem Speichern gesetzt ist
        assertEquals(email, savedInstructor.getEmail());
    }
}
