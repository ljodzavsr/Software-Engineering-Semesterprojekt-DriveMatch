package ch.zhaw.drivematch.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MailTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Mail mail = new Mail();

        // Act
        mail.setTo("recipient@example.com");
        mail.setSubject("Test Subject");
        mail.setMessage("Test Message");

        // Assert
        assertEquals("recipient@example.com", mail.getTo());
        assertEquals("Test Subject", mail.getSubject());
        assertEquals("Test Message", mail.getMessage());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        Mail mail = new Mail();

        // Assert
        assertNotNull(mail);
        assertNull(mail.getTo());
        assertNull(mail.getSubject());
        assertNull(mail.getMessage());
    }
}
