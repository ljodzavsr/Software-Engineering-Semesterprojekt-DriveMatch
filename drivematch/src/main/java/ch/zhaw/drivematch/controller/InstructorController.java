package ch.zhaw.drivematch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.drivematch.model.Instructor;
import ch.zhaw.drivematch.model.InstructorCreateDTO;
import ch.zhaw.drivematch.repository.InstructorRepository;

@RestController
@RequestMapping("/api")

public class InstructorController {

    @Autowired
    InstructorRepository instructorRepository;

    @PostMapping("/instructor")
    public ResponseEntity<Instructor> createInstructor(
            @RequestBody InstructorCreateDTO fDTO) {
        Instructor fDAO = new Instructor(fDTO.getEmail(), fDTO.getName());
        Instructor f = instructorRepository.save(fDAO);
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }

    @GetMapping("/instructor")
    public ResponseEntity<List<Instructor>> getAllInstructor() {
        List<Instructor> allFree = instructorRepository.findAll();
        return new ResponseEntity<>(allFree, HttpStatus.OK);
    }

    @GetMapping("/instructor/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable String id) {
        Optional<Instructor> optInstructor = instructorRepository.findById(id);
        if (optInstructor.isPresent()) {
            return new ResponseEntity<>(optInstructor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
