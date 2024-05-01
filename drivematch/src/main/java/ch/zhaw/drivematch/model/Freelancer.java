package ch.zhaw.drivematch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Document("freelancer")
public class Freelancer {
    @Id
    private String id;
    @NonNull
    private String email;
    @NonNull
    private String name; 
}
