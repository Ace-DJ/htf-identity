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

    @Override
    public String toString() {
        return String.format("Investigation:%s \nAttempts: %s \n Outcome: %s",investigation,attemptsRemaining,outcome);
    }
}
