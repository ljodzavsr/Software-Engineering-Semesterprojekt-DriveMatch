package ch.zhaw.drivematch.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonStateChangeDTO;
import ch.zhaw.drivematch.service.LessonService;

@RestController
@RequestMapping("/api/service")
public class ServiceController {
    
    @Autowired
    LessonService lessonService;

    @PutMapping("/assignlesson")
    public ResponseEntity<Lesson> assignLesson(@RequestBody LessonStateChangeDTO changeS) {
        String instructorEmail = changeS.getInstructorEmail();
        String lessonId = changeS.getLessonId();
        Optional<Lesson> lesson = lessonService.assignLesson(lessonId, instructorEmail);
        if (lesson.isPresent()) {
            return new ResponseEntity<>(lesson.get(), HttpStatus.OK); 
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/completelesson")
    public ResponseEntity<Lesson> completeLesson(@RequestBody LessonStateChangeDTO changeS) {
        String instructorEmail = changeS.getInstructorEmail();
        String lessonId = changeS.getLessonId();
        Optional<Lesson> lesson = lessonService.completeLesson(lessonId, instructorEmail);
        if (lesson.isPresent()) {
            return new ResponseEntity<>(lesson.get(), HttpStatus.OK); 
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
