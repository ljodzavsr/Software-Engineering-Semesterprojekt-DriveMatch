package ch.zhaw.drivematch.model;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LessonStateAggregation {
    private String id;
    private List<String> lessonIds;
    private String count;

}
