package ch.zhaw.drivematch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JobCreateDTO {
    private String description;
    private JobType jobType;
    private Double earnings;
}
