package be.springtheory.htf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Case {
    private final UUID uuid;
    private final List<Investigation> investigationList;
}
