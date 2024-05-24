package ch.zhaw.drivematch.model.chatGPT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class MessagesTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Messages messages = new Messages();
        ArrayList<Message> messageList = new ArrayList<>();
        messageList.add(new Message());

        // Act
        messages.setMessages(messageList);

        // Assert
        assertEquals("gpt-3.5-turbo", messages.getModel());
        assertEquals(messageList, messages.getMessages());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        Messages messages = new Messages();

        // Assert
        assertNotNull(messages);
        assertEquals("gpt-3.5-turbo", messages.getModel());
        assertNull(messages.getMessages());
    }
}
