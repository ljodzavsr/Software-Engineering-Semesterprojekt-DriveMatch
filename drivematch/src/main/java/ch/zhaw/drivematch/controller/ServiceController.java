package ch.zhaw.drivematch.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonStateChangeDTO;
import ch.zhaw.drivematch.service.LessonService;
import ch.zhaw.drivematch.service.RoleService;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    @Autowired
    LessonService lessonService;

    @Autowired
    RoleService roleService;

    @PutMapping("/assignlesson")
    public ResponseEntity<Lesson> assignLesson(@RequestBody LessonStateChangeDTO changeS, @AuthenticationPrincipal Jwt jwt) {
        if (!roleService.hasRole("admin", jwt)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        String instructorEmail = changeS.getInstructorEmail();
        String lessonId = changeS.getLessonId();
        Optional<Lesson> lesson = lessonService.assignLesson(lessonId, instructorEmail);
        if (lesson.isPresent()) {
            return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/completelesson")
    public ResponseEntity<Lesson> completeLesson(@RequestBody LessonStateChangeDTO changeS, @AuthenticationPrincipal Jwt jwt) {
        if (!roleService.hasRole("admin", jwt)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        String instructorEmail = changeS.getInstructorEmail();
        String lessonId = changeS.getLessonId();
        Optional<Lesson> lesson = lessonService.completeLesson(lessonId, instructorEmail);
        if (lesson.isPresent()) {
            return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/me/assignlesson")
    public ResponseEntity<Lesson> assignToMe(@RequestParam String lessonId,
            @AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getClaimAsString("email");
        Optional<Lesson> lesson = lessonService.assignLesson(lessonId, userEmail);
        if (lesson.isPresent()) {
            return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/me/completelesson")
    public ResponseEntity<Lesson> completeMyLesson(@RequestParam String lessonId,
            @AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getClaimAsString("email");
        Optional<Lesson> lesson = lessonService.completeLesson(lessonId, userEmail);
        if (lesson.isPresent()) {
            return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
