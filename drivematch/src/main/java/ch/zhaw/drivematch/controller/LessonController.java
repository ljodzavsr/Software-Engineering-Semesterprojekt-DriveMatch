package ch.zhaw.drivematch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonCreateDTO;
import ch.zhaw.drivematch.repository.LessonRepository;

@RestController
@RequestMapping("/api")
public class LessonController {

    @Autowired
    LessonRepository lessonRepository;

    @PostMapping("/lesson")
    public ResponseEntity<Lesson> createLesson(@RequestBody LessonCreateDTO cDTO) {
        Lesson jDAO = new Lesson(cDTO.getDescription(), cDTO.getLessonType(), cDTO.getPrice());
        Lesson j = lessonRepository.save(jDAO);
        return new ResponseEntity<>(j, HttpStatus.CREATED);
    }

    @GetMapping("/lesson")
    public ResponseEntity<List<Lesson>> getAllLesson() {
        List<Lesson> allLesson = lessonRepository.findAll();
        return new ResponseEntity<>(allLesson, HttpStatus.OK);
    }
}
