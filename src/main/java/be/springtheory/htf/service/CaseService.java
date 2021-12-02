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
        log.info("Cracking new case!");
        //Create the case
        final Case htfCase = restService.createCase();

        //Try to solve as many investigations as possible
        final Set<String> cluesSet =investigationService.solveInvestigations(Arrays.asList(htfCase.getInvestigations()));

        //Try to find a possible suspect
        final List<Suspect> suspects =suspectService.findPossibleSuspects(cluesSet);

        CaseResult result;

        switch (suspects.size()){
            case 0:{
                //If we find no suspects, solve the case with the first suspect
                log.info("No suspects found!");
                result=restService.closeCase(htfCase.getCaseId(),suspectService.getAllSuspects().get(0).getId());
                break;
            }
            case 1:{
                //If we have a single match, solve the case with this suspect
                log.info("Single suspect found! guessing {}",suspects.get(0).getName());
                result= restService.closeCase(htfCase.getCaseId(),suspects.get(0).getId());
            }
            default:{
                //Solve the case with the first suspect we can found
                log.info("Found {} suspects! guessing {}",suspects.size(),suspects.get(0).getName());
                result= restService.closeCase(htfCase.getCaseId(),suspects.get(0).getId());
            }
        }
        if(result.getResult().equals("INNOCENT")) {
            log.info("I was wrong, it was {}, not {}",result.getMurderer().getName(),result.getAccusedSuspect().getName());
        } else {
            log.info("I was right, the murderer was {}",result.getMurderer().getName());
        }

        //Add all our found clues to the murderer
        suspectService.addCluesToMurder(result,cluesSet);
    }
}
