package ch.zhaw.drivematch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Document("lesson")
public class Lesson {
    @Id
    private String id;
    @NonNull
    private String description;
    @NonNull
    private LessonType lessonType;
    @NonNull
    private Double price;
    private LessonState lessonState = LessonState.NEW;
    private String instructorId;
}
