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

// Der LessonController bietet Endpunkte zur Erstellung, Abfrage, Aggregation und Löschung von Lektionen. 
// Er verwendet LessonRepository für Datenbankoperationen, RoleService zur Rollenüberprüfung und ChatGPTService zur Kommunikation mit 
// ChatGPT für die automatische Erstellung von Lektionstexten. Die Endpunkte sind so konzipiert, dass sie basierend auf 
// den Benutzerrollen und angegebenen Filtern verschiedene Operationen durchführen.

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
        var chatGPTResponse = chatGPTService.chatWithChatGpt(
                "Erstelle mir eine kurze Beschreibung für die Fahrstunde (Preis in Schweizer Franken). Du bekommst von mir jeweils eine description, den Lessontype (fundamentals, urban, highway, parking - das sind die 4 möglichen typen) und den Preis bekommst du auch. Siehe folgende Infos: "
                        + cDTO.getDescription() + cDTO.getLessonType() + cDTO.getPrice());
        var choice = chatGPTResponse.getChoices().stream().findFirst().orElseThrow();
        Lesson jDAO = new Lesson(cDTO.getDescription(), choice.getMessage().getContent(), cDTO.getLessonType(),
                cDTO.getPrice());
        Lesson j = lessonRepository.save(jDAO);
        return new ResponseEntity<>(j, HttpStatus.CREATED);
    }

    @GetMapping("/lesson")
    public ResponseEntity<Page<Lesson>> getAllLessons(
            @RequestParam(required = false) Double max,
            @RequestParam(required = false) LessonType type,
            @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "2") Integer pageSize) {
        Page<Lesson> allLessons;
        if (max == null && type == null) {
            allLessons = lessonRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
        } else {
            if (max != null && type != null) {
                allLessons = lessonRepository.findByLessonTypeAndPriceLessThanEqual(type, max,
                        PageRequest.of(pageNumber - 1, pageSize));
            } else if (max != null) {
                allLessons = lessonRepository.findByPriceLessThanEqual(max,
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
