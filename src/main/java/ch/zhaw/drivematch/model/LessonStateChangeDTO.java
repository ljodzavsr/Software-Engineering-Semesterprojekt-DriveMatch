package ch.zhaw.drivematch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LessonStateChangeDTO {
    private String lessonId;
    private String instructorEmail;
}
