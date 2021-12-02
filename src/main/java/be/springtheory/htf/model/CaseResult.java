package be.springtheory.htf.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CaseResult {
    private Suspect accusedSuspect;
    private Suspect murderer;
    private String result;

    @Override
    public String toString() {
        return  "accusedSuspect=" + accusedSuspect +
                ", murderer=" + murderer +
                ", result='" + result + '\'' +
                '}';
    }
}
