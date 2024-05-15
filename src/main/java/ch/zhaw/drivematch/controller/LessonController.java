package ch.zhaw.drivematch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonCreateDTO;
import ch.zhaw.drivematch.model.LessonStateAggregation;
import ch.zhaw.drivematch.model.LessonType;
import ch.zhaw.drivematch.repository.LessonRepository;
import ch.zhaw.drivematch.service.ChatGPTService;
import ch.zhaw.drivematch.service.RoleService;

@RestController
@RequestMapping("/api")
public class LessonController {

    @Autowired
    LessonRepository lessonRepository;

    @Autowired
    RoleService roleService;

    @Autowired
    ChatGPTService chatGPTService;

    @PostMapping("/lesson")
    public ResponseEntity<Lesson> createLesson(@RequestBody LessonCreateDTO cDTO, @AuthenticationPrincipal Jwt jwt) {
        if (!roleService.hasRole("admin", jwt)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
         var chatGPTResponse = chatGPTService.chatWithChatGpt("Erstelle mir eine kurze Beschreibung für die Fahrstunde (Preis in Schweizer Franken): " + cDTO.getDescription() + cDTO.getLessonType() + cDTO.getPrice());
        var choice = chatGPTResponse.getChoices().stream().findFirst().orElseThrow();
        Lesson jDAO = new Lesson(cDTO.getDescription(), choice.getMessage().getContent(), cDTO.getLessonType(), cDTO.getPrice());
        Lesson j = lessonRepository.save(jDAO);
        return new ResponseEntity<>(j, HttpStatus.CREATED);
    }

    @GetMapping("/lesson")
    public ResponseEntity<Page<Lesson>> getAllLessons(
            @RequestParam(required = false) Double min,
            @RequestParam(required = false) LessonType type,
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        Page<Lesson> allLessons;
        if (min == null && type == null) {
            allLessons = lessonRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
        } else {
            if (min != null && type != null) {
                allLessons = lessonRepository.findByLessonTypeAndPriceGreaterThan(type, min,
                        PageRequest.of(pageNumber - 1, pageSize));
            } else if (min != null) {
                allLessons = lessonRepository.findByPriceGreaterThan(min,
                        PageRequest.of(pageNumber - 1, pageSize));
            } else {
                allLessons = lessonRepository.findByLessonType(type, PageRequest.of(pageNumber - 1,
                        pageSize));
            }
        }
        return new ResponseEntity<>(allLessons, HttpStatus.OK);
    }

    @GetMapping("/lesson/aggregation/state")
    public List<LessonStateAggregation> getLessonStateAggregation() {
        return lessonRepository.getLessonStateAggregation();
    }

    @DeleteMapping("/lesson")
    public ResponseEntity<String> deleteAllLessons(@AuthenticationPrincipal Jwt jwt) {
        if (!roleService.hasRole("admin", jwt)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        lessonRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("DELETED");
    }

}
