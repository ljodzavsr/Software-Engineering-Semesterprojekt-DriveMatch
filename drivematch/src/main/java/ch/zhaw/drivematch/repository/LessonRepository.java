package ch.zhaw.drivematch.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import ch.zhaw.drivematch.model.Lesson;
import ch.zhaw.drivematch.model.LessonStateAggregation;
import ch.zhaw.drivematch.model.LessonType;

public interface LessonRepository extends MongoRepository<Lesson,String>{
    List<Lesson> findByPriceGreaterThan(Double price);
    List<Lesson> findByLessonType(LessonType lessonType);
    @Aggregation("{$group:{_id:'$lessonState',lessonIds:{$push:'$_id',},count:{$count:{}}}}")
    List<LessonStateAggregation> getLessonStateAggregation();
}
