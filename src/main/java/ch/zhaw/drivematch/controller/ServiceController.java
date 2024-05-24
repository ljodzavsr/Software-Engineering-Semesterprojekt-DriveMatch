package ch.zhaw.drivematch.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonState;
import ch.zhaw.drivematch.model.Mail;
import ch.zhaw.drivematch.service.LessonService;
import ch.zhaw.drivematch.service.MailService;
import ch.zhaw.drivematch.service.RoleService;

@RestController
@RequestMapping("/api/service")
public class ServiceController {

    @Autowired
    LessonService lessonService;

    @Autowired
    RoleService roleService;

    @Autowired
    MailService mailService;

    @PutMapping("/me/assignlesson")
    public ResponseEntity<Lesson> assignToMe(@RequestParam String lessonId,
            @AuthenticationPrincipal Jwt jwt) {
        String userEmail = jwt.getClaimAsString("email");
        Optional<Lesson> lesson = lessonService.assignLesson(lessonId, userEmail);
        if (lesson.isPresent()) {
            sendMail(userEmail, lesson);
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
            sendMail(userEmail, lesson);
            return new ResponseEntity<>(lesson.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private void sendMail(String instructorEmail, Optional<Lesson> lesson) {
        var mail = new Mail();
        mail.setTo(instructorEmail);
        mail.setSubject(
                "Assigned lesson " + lesson.get().getDescription() + " with status " + lesson.get().getLessonState());

        String mailMessage = "Hi, the lesson " + lesson.get().getDescription()
                + " was assigned to you. The new status is " + lesson.get().getLessonState();
        if (lesson.isPresent() && lesson.isPresent() && lesson.get().getLessonState().equals(LessonState.DONE)) {
            mailMessage = "Hi, the lesson " + lesson.get().getDescription() + " was marked as "
                    + lesson.get().getLessonState();
        }
        mail.setMessage(mailMessage);
        mailService.sendMail(mail);
    }
}
