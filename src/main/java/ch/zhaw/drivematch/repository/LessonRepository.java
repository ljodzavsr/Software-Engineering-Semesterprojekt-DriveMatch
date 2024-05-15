package ch.zhaw.drivematch.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonStateAggregation;
import ch.zhaw.drivematch.model.LessonType;

public interface LessonRepository extends MongoRepository<Lesson, String> {
    Page<Lesson> findByPriceGreaterThan(Double price, Pageable pageable);

    Page<Lesson> findByLessonType(LessonType lessonType, Pageable pageable);

    Page<Lesson> findByLessonTypeAndPriceGreaterThan(LessonType lessonType, Double price, Pageable pageable);

    @Aggregation("{$group:{_id:'$lessonState',lessonIds:{$push:'$_id',},count:{$count:{}}}}")
    List<LessonStateAggregation> getLessonStateAggregation();
}
