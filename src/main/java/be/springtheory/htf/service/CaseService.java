package be.springtheory.htf.service;

import be.springtheory.htf.model.Case;
import be.springtheory.htf.model.CaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CaseService {
    private final RestService restService;
    private final SuspectService suspectService;

    public CaseService(RestService restService, SuspectService suspectService) {
        this.restService = restService;
        this.suspectService = suspectService;
    }

    public void crackCase() {
        log.info("Cracking new case");
        final Case htfCase = restService.getCase();
        log.info("Got case with Id {}",htfCase.getCaseId());

        log.info("Closing case with Id {}",htfCase.getCaseId());
        final CaseResult caseResult = restService.closeCase(htfCase.getCaseId(),suspectService.getAllSuspects().get(0).getId());

        log.info(caseResult.toString());
    }
}
