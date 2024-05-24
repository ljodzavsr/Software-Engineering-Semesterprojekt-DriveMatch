package ch.zhaw.drivematch.model.chatGPT;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MessageTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Message message = new Message();

        // Act
        message.setRole("user");
        message.setContent("This is a test message");

        // Assert
        assertEquals("user", message.getRole());
        assertEquals("This is a test message", message.getContent());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        Message message = new Message();

        // Assert
        assertNotNull(message);
    }
}
