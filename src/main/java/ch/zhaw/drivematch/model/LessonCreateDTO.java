package ch.zhaw.drivematch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LessonCreateDTO {
    private String description;
    private LessonType lessonType;
    private Double price;
}
