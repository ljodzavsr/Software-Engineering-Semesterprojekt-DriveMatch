package ch.zhaw.drivematch.model.chatGPT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class MessageResponseTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        MessageResponse messageResponse = new MessageResponse();
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice());
        Usage usage = new Usage();

        // Act
        messageResponse.setId("test-id");
        messageResponse.setObject("test-object");
        messageResponse.setCreated(123456789);
        messageResponse.setChoices(choices);
        messageResponse.setUsage(usage);

        // Assert
        assertEquals("test-id", messageResponse.getId());
        assertEquals("test-object", messageResponse.getObject());
        assertEquals(123456789, messageResponse.getCreated());
        assertEquals(choices, messageResponse.getChoices());
        assertEquals(usage, messageResponse.getUsage());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        MessageResponse messageResponse = new MessageResponse();

        // Assert
        assertNotNull(messageResponse);
    }
}
