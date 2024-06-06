package ch.zhaw.drivematch.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import ch.zhaw.drivematch.repository.InstructorRepository;

// Die SecurityConfig-Klasse konfiguriert die Sicherheitsrichtlinien der Anwendung, einschließlich der Definition von zugänglichen und 
// geschützten URL-Mustern sowie der Einrichtung der JWT-basierten Authentifizierung für OAuth2-Resource-Server. 
// Sie stellt sicher, dass JWTs korrekt dekodiert und validiert werden, wobei benutzerdefinierte Validierungen integriert sind.

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html
    // https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    String issuerUri;

    @Autowired
    InstructorRepository instructorRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/*").permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/**").permitAll())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }

    @Bean
    @ConditionalOnMissingBean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromIssuerLocation(issuerUri);
        OAuth2TokenValidator<Jwt> userValidator = new UserValidator(instructorRepository);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuerUri);
        OAuth2TokenValidator<Jwt> myValidator = new DelegatingOAuth2TokenValidator<>(withIssuer, userValidator);
        jwtDecoder.setJwtValidator(myValidator);
        return jwtDecoder;
    }
}