package ch.zhaw.drivematch.service;

import org.slf4j.Logger;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;


// Der Code erstellt eine Filterfunktion, die für jede HTTP-Anfrage, die durch den WebClient geht, die Methode, URL und alle Header 
// in den Logs protokolliert. Dies ist nützlich für das Debugging und Überwachen von HTTP-Anfragen in einer reaktiven Anwendung mit Spring WebFlux.

public class ServiceUtils {
    public static ExchangeFilterFunction logRequest(Logger logger) {
        return (clientRequest, next) -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers()
                    .forEach((name, values) -> values.forEach(value -> logger.info("{}={}", name, value)));
            return next.exchange(clientRequest);
        };
    }
}