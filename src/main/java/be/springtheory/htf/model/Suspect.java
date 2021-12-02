package be.springtheory.htf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Suspect {
    private final UUID id;
    private final String name;
}
