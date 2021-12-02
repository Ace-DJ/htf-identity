package be.springtheory.htf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Investigation {
    private int attemptsRemaining;
    private final UUID id;
    private final String investigation;
    private final String investigationParameters;
    private final String outcome;
}
