package ch.zhaw.drivematch.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class MailInformationTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        MailInformation mailInformation = new MailInformation();

        // Act
        mailInformation.setFormat(true);
        mailInformation.setAlias(false);
        mailInformation.setDomain("example.com");
        mailInformation.setDisposable(true);
        mailInformation.setDns(false);

        // Assert
        assertTrue(mailInformation.isFormat());
        assertFalse(mailInformation.isAlias());
        assertEquals("example.com", mailInformation.getDomain());
        assertTrue(mailInformation.isDisposable());
        assertFalse(mailInformation.isDns());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange & Act
        MailInformation mailInformation = new MailInformation();

        // Assert
        assertNotNull(mailInformation);
        assertFalse(mailInformation.isFormat());
        assertFalse(mailInformation.isAlias());
        assertNull(mailInformation.getDomain());
        assertFalse(mailInformation.isDisposable());
        assertFalse(mailInformation.isDns());
    }
}
