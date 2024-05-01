package ch.zhaw.drivematch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JobStateChangeDTO {
    private String jobId;
    private String freelancerEmail;
}
