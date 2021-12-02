package be.springtheory.htf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Investigation {
    private String attemptsRemaining;
    private  String id;
    private  String investigation;
    private  String investigationParameters;
    private  String outcome;
}
