package be.springtheory.htf.service;

import be.springtheory.htf.model.Suspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SuspectService {
    private List<Suspect> possibleSuspects ;
    private final RestService restService;

    @Autowired
    public SuspectService(RestService restService) {
        this.restService = restService;
    }

    public List<Suspect> getAllSuspects() {
        if (possibleSuspects == null) {
            possibleSuspects = restService.getSuspects();
        }
        return possibleSuspects;
    }
}
