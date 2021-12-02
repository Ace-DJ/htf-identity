package be.springtheory.htf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Case {
    private String caseId;
    private Investigation[] investigations;
}
