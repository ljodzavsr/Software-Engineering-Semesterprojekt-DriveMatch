package ch.zhaw.drivematch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LessonCreateDTO {
    private String description;
    private LessonType lessonType;
    private Double price;
}
