package ch.zhaw.drivematch.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InstructorCreateDTO {
    private String email;
    private String name;
    private String lastname;
}
