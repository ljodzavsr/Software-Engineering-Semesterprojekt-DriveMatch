package ch.zhaw.drivematch.service;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import ch.zhaw.drivematch.model.MailInformation;

// Die MailValidatorService-Klasse verwendet einen WebClient, um E-Mail-Adressen über eine externe API zu validieren. 
// Sie konfiguriert den WebClient mit Basis-URL, Standard-Headern und einem Logging-Filter, und führt dann die Validierung 
// durch eine GET-Anfrage durch, wobei die Antwort in ein MailInformation-Objekt umgewandelt wird.

@Service
public class MailValidatorService {
    private static final String DISIFY_EMAIL_VALIDATOR_BASE_URL = "https://www.disify.com";

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
    private static final String USER_AGENT = "Spring 5 WebClient";
    private static final Logger logger = LoggerFactory.getLogger(MailValidatorService.class);
    private final WebClient webClient;

    @Autowired
    public MailValidatorService() {
        this.webClient = WebClient.builder()
                .baseUrl(DISIFY_EMAIL_VALIDATOR_BASE_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.USER_AGENT, USER_AGENT)
                .filter(ServiceUtils.logRequest(logger))
                .build();
    }

    public MailInformation validateEmail(String email) {
        return webClient
                // http-method
                .get()
                // uri to api
                .uri("/api/email/" + email)
                // Proceed to declare how to extract the response.
                .retrieve()
                // mapping to a certain class
                .bodyToMono(MailInformation.class)
                // Optional: pass a timeout for the request
                // Blocks until next signal is received or the timeout expires
                .block(REQUEST_TIMEOUT);
    }
}