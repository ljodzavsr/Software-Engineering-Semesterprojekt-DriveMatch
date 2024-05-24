package ch.zhaw.drivematch.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import ch.zhaw.drivematch.model.Mail;

public class MailServiceTest {

    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private MailService mailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Mock the getJavaMailSender method to return the mocked javaMailSender
        mailService = spy(mailService);
        doReturn(javaMailSender).when(mailService).getJavaMailSender();
    }

    @Test
    public void testSendMail() {
        // Arrange
        Mail mail = new Mail();
        mail.setTo("recipient@example.com");
        mail.setSubject("Test Subject");
        mail.setMessage("Test Message");

        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));

        // Act
        boolean result = mailService.sendMail(mail);

        // Assert
        assertTrue(result);
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testSendMailException() {
        // Arrange
        Mail mail = new Mail();
        mail.setTo("recipient@example.com");
        mail.setSubject("Test Subject");
        mail.setMessage("Test Message");

        doThrow(new RuntimeException("Mail sending failed")).when(javaMailSender).send(any(SimpleMailMessage.class));

        // Act
        boolean result = mailService.sendMail(mail);

        // Assert
        assertFalse(result);
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}
