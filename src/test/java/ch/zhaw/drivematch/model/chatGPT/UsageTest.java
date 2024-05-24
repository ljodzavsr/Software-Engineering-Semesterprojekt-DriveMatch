package ch.zhaw.drivematch.model.chatGPT;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UsageTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Usage usage = new Usage();

        // Act
        usage.setPrompt_tokens(10);
        usage.setCompletion_tokens(20);
        usage.setTotal_tokens(30);

        // Assert
        assertEquals(10, usage.getPrompt_tokens());
        assertEquals(20, usage.getCompletion_tokens());
        assertEquals(30, usage.getTotal_tokens());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        Usage usage = new Usage();

        // Assert
        assertNotNull(usage);
    }
}
