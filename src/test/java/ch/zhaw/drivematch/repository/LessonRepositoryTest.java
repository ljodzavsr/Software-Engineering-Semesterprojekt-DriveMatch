package ch.zhaw.drivematch.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonState;
import ch.zhaw.drivematch.model.LessonStateAggregation;
import ch.zhaw.drivematch.model.LessonType;

@DataMongoTest
public class LessonRepositoryTest {

    @Autowired
    private LessonRepository lessonRepository;

    @BeforeEach
    public void setUp() {
        lessonRepository.deleteAll();
    }

    @Test
    public void testFindByPriceLessThanEqual() {
        // Arrange
        Lesson lesson1 = new Lesson("desc1", "detailDesc1", LessonType.BEGINNER, 50.0);
        Lesson lesson2 = new Lesson("desc2", "detailDesc2", LessonType.BEGINNER, 100.0);
        lessonRepository.save(lesson1);
        lessonRepository.save(lesson2);

        // Act
        Page<Lesson> result = lessonRepository.findByPriceLessThanEqual(75.0, PageRequest.of(0, 10));

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals("desc1", result.getContent().get(0).getDescription());
    }

    @Test
    public void testFindByLessonType() {
        // Arrange
        Lesson lesson1 = new Lesson("desc1", "detailDesc1", LessonType.BEGINNER, 50.0);
        Lesson lesson2 = new Lesson("desc2", "detailDesc2", LessonType.ADVANCED, 100.0);
        lessonRepository.save(lesson1);
        lessonRepository.save(lesson2);

        // Act
        Page<Lesson> result = lessonRepository.findByLessonType(LessonType.BEGINNER, PageRequest.of(0, 10));

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals("desc1", result.getContent().get(0).getDescription());
    }

    @Test
    public void testFindByLessonTypeAndPriceLessThanEqual() {
        // Arrange
        Lesson lesson1 = new Lesson("desc1", "detailDesc1", LessonType.BEGINNER, 50.0);
        Lesson lesson2 = new Lesson("desc2", "detailDesc2", LessonType.BEGINNER, 100.0);
        lessonRepository.save(lesson1);
        lessonRepository.save(lesson2);

        // Act
        Page<Lesson> result = lessonRepository.findByLessonTypeAndPriceLessThanEqual(LessonType.BEGINNER, 75.0, PageRequest.of(0, 10));

        // Assert
        assertEquals(1, result.getTotalElements());
        assertEquals("desc1", result.getContent().get(0).getDescription());
    }

    @Test
    public void testGetLessonStateAggregation() {
        // Arrange
        Lesson lesson1 = new Lesson("desc1", "detailDesc1", LessonType.BEGINNER, 50.0);
        lesson1.setLessonState(LessonState.NEW);
        Lesson lesson2 = new Lesson("desc2", "detailDesc2", LessonType.ADVANCED, 100.0);
        lesson2.setLessonState(LessonState.DONE);
        lessonRepository.save(lesson1);
        lessonRepository.save(lesson2);

        // Act
        List<LessonStateAggregation> result = lessonRepository.getLessonStateAggregation();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(agg -> agg.getId().equals("NEW") && agg.getCount().equals("1")));
        assertTrue(result.stream().anyMatch(agg -> agg.getId().equals("DONE") && agg.getCount().equals("1")));
    }
}
