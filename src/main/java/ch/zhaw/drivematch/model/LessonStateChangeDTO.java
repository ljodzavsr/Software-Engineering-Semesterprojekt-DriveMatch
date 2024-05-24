package ch.zhaw.drivematch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LessonStateChangeDTO {
    private String lessonId;
    private String instructorEmail;
}
