package ch.zhaw.drivematch.security;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import ch.zhaw.drivematch.model.Instructor;
import ch.zhaw.drivematch.repository.InstructorRepository;

class UserValidator implements OAuth2TokenValidator<Jwt> {

    InstructorRepository instructorRepository;

    public UserValidator(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public OAuth2TokenValidatorResult validate(Jwt jwt) {
        OAuth2Error error = new OAuth2Error("invalid_token", "The required email is missing", null);

        String userEmail = jwt.getClaimAsString("email");
        if (userEmail != null && !userEmail.equals("")) {
            Instructor f = instructorRepository.findFirstByEmail(userEmail);
            if (f == null) {
                String name = jwt.getClaimAsString("given_name");
                String lastname = jwt.getClaimAsString("family_name");
                instructorRepository.save(new Instructor(userEmail, name, lastname));
                System.out.println("Creating new user:");
                System.out.println("  - name:  " + name);
                System.out.println("  - email: " + userEmail);
            }
            return OAuth2TokenValidatorResult.success();
        }
        return OAuth2TokenValidatorResult.failure(error);
    }
}
