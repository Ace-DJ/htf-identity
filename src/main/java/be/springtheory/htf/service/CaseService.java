package be.springtheory.htf.service;

import be.springtheory.htf.model.Case;
import be.springtheory.htf.model.CaseResult;
import be.springtheory.htf.model.Suspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class CaseService {
    private final RestService restService;
    private final SuspectService suspectService;
    private final InvestigationService investigationService;

    public CaseService(RestService restService, SuspectService suspectService, InvestigationService investigationService) {
        this.restService = restService;
        this.suspectService = suspectService;
        this.investigationService = investigationService;
    }

    public void crackCase() {
        log.info("Cracking new case");
        final Case htfCase = restService.createCase();
        log.info("Got case with Id {}",htfCase.getCaseId());
        Set<String> cluesSet =investigationService.solveInvestigations(Arrays.asList(htfCase.getInvestigations()));

        List<Suspect> suspects =suspectService.findPossibleSuspects(cluesSet);
        CaseResult result;
        switch (suspects.size()){
            case 0:{
                log.info("No suspects found!");
                result=restService.closeCase(htfCase.getCaseId(),suspectService.getAllSuspects().get(0).getId());
                break;
            }
            case 1:{
                log.info("Single suspect found! {}",suspects.get(0).getName());
                result= restService.closeCase(htfCase.getCaseId(),suspects.get(0).getId());
            }
            default:{
                log.info("More then one suspect found!");
                result= restService.closeCase(htfCase.getCaseId(),suspects.get(0).getId());
            }
        }
        log.info("Result: {}",result.getResult());

        suspectService.addCluesToMurder(result,cluesSet);
        //final CaseResult caseResult = restService.closeCase(htfCase.getCaseId(),suspectService.getAllSuspects().get(0).getId());

        //log.info(caseResult.toString());
    }
}
