package ch.zhaw.drivematch.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Name {
    private String name;
    private String geschlecht;
    private int anzahl;
}

