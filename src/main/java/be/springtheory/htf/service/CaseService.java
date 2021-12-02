package be.springtheory.htf.service;

import be.springtheory.htf.model.Case;
import org.springframework.stereotype.Service;

@Service
public class CaseService {
    private final RestService restService;

    public CaseService(RestService restService) {
        this.restService = restService;
    }

    public void crackCase() {
        final Case htfCase = restService.getCase();
    }
}
