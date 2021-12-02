package be.springtheory.htf.service;

import be.springtheory.htf.investigation.AncientAlgorithmInvestigation;
import be.springtheory.htf.investigation.InvestigationStrategy;
import be.springtheory.htf.model.Investigation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class InvestigationService {
    private final RestService restService;
    private final List<InvestigationStrategy> investigationStrategyStrategies;

    @Autowired
    public InvestigationService(RestService restService, List<InvestigationStrategy> investigationStrategyStrategies) {
        this.restService = restService;
        this.investigationStrategyStrategies = investigationStrategyStrategies;
    }

    public Set<String> solveInvestigations(List<Investigation> investigations){
        Set<String> clues = new HashSet<>();

        //Iterate over all investigations in our case
        for (final Investigation investigation : investigations) {

            var investigationStrategyOptional = getInvestigation(investigation.getInvestigation());
            //If we have a solver for our investigation, run it
            if(investigationStrategyOptional.isPresent()) {
                switch (investigation.getAttemptsRemaining()) {
                    case "Unlimited": {
                        solveUnlimited(clues,investigationStrategyOptional.get(),investigation);
                        break;
                    }
                    case "1": {
                        solveSingle(clues,investigationStrategyOptional.get(),investigation);
                        break;
                    }
                }
            }else {
                log.warn("Could not find investigation for {}",investigation.getInvestigation());
            }
        }
        return clues;
    }

    private void solveSingle(Set<String> clues,  InvestigationStrategy investigationStrategy, Investigation investigation) {
        //Solve the investigation
        final String answer = investigationStrategy.solve(investigation.getInvestigationParameters());
        final Investigation investigationAnswer = restService.solveSingle(investigation.getId(),answer);
        //Add our new clue to clues
        clues.add(investigationAnswer.getOutcome());
        log.info("Solved investigation {} with outcome {}",investigationAnswer.getInvestigation(),investigationAnswer.getOutcome());
    }

    private void solveUnlimited(Set<String> clues,InvestigationStrategy investigationStrategy, Investigation investigation) {
        Investigation investigationAnswer=null;
        if(investigationStrategy instanceof AncientAlgorithmInvestigation) {
            //Iterate over every shift
            for (int i = 0; i < 27; i++) {
               String answer = investigationStrategy.solve(String.format("{\"cipher\":\"%s\",\"shift\":\"%d\"}",investigation.getInvestigationParameters(),i));
               investigationAnswer= restService.solveMultiple(investigation.getId(),answer);
               //our rest call will return null if it was incorrect
                if(investigationAnswer !=null){
                    clues.add(investigationAnswer.getOutcome());
                    log.info("Solved investigation {} with clue {}",investigationAnswer.getInvestigation(),investigationAnswer.getOutcome());
                    break;
                }
            }
        }
    }

    private Optional<InvestigationStrategy> getInvestigation(String investigationString) {
        return investigationStrategyStrategies.stream().filter(investigation1 -> investigation1.getInvestigationName().equals(investigationString)).findFirst();
    }
}
