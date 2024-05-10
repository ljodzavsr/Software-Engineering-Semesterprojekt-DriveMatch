package ch.zhaw.drivematch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Lesson>> getAllLessons(
                @RequestParam(required = false) Double min,
                @RequestParam(required = false) LessonType type) {
        List<Lesson> allLessons;
        if (min == null && type == null) {
            allLessons = lessonRepository.findAll();
        } else {
            if (min != null && type != null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else if (min != null) {
                allLessons = lessonRepository.findByPriceGreaterThan(min);
            } else {
                allLessons = lessonRepository.findByLessonType(type);
            }
        }
        return new ResponseEntity<>(allLessons, HttpStatus.OK);
    }

    @GetMapping("/lesson/aggregation/state")
    public List<LessonStateAggregation> getLessonStateAggregation() {
        return lessonRepository.getLessonStateAggregation();
    }
}