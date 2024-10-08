package ch.zhaw.drivematch.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.drivematch.model.Instructor;
import ch.zhaw.drivematch.repository.InstructorRepository;
import ch.zhaw.drivematch.service.MailValidatorService;
import ch.zhaw.drivematch.service.RoleService;

// Der InstructorController bietet Endpunkte zur Abfrage und Verwaltung von Instruktoren. Er ermöglicht es Administratoren, alle 
// Instruktoren paginiert abzurufen, einen bestimmten Instruktor anhand der ID zu suchen und ihre eigenen Instruktorendaten basierend 
// auf der E-Mail-Adresse zu erhalten. Der Controller verwendet InstructorRepository für Datenbankoperationen und RoleService 
// zur Überprüfung von Benutzerrollen.

@RestController
@RequestMapping("/api")
public class InstructorController {

    @Autowired
    InstructorRepository instructorRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    MailValidatorService mailValidatorService;

    @GetMapping("/instructor")
    public ResponseEntity<Page<Instructor>> getAllInstructor(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        if (!roleService.hasRole("admin", jwt)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Page<Instructor> allFree = instructorRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
        return new ResponseEntity<>(allFree, HttpStatus.OK);
    }

    @GetMapping("/instructor/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        if (!roleService.hasRole("admin", jwt)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Optional<Instructor> optInstructor = instructorRepository.findById(id);
        if (optInstructor.isPresent()) {
            return new ResponseEntity<>(optInstructor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/me/instructor")
    public ResponseEntity<Instructor> getMyInstructorId(@AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getClaimAsString("email");
        Instructor instructor = instructorRepository.findFirstByEmail(userEmail);
        if (instructor != null) {
            return new ResponseEntity<>(instructor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
