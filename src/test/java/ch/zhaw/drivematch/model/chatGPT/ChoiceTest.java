package ch.zhaw.drivematch.model.chatGPT;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ChoiceTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Choice choice = new Choice();
        Message message = new Message();
        message.setContent("Test message");

        // Act
        choice.setIndex(1);
        choice.setMessage(message);
        choice.setFinish_reason("finished");

        // Assert
        assertEquals(1, choice.getIndex());
        assertEquals("Test message", choice.getMessage().getContent());
        assertEquals("finished", choice.getFinish_reason());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        Choice choice = new Choice();

        // Assert
        assertNotNull(choice);
    }
}
