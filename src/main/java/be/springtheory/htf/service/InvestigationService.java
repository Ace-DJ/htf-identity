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

    public Set<String> solveInvestigations(List<Investigation> investigationStrategyList){
        Set<String> clues = new HashSet<>();
        for (final Investigation investigation : investigationStrategyList) {
            var investigationOptional = getInvestigation(investigation.getInvestigation());
            if(investigationOptional.isPresent()) {
                if(investigation.getAttemptsRemaining().equals("Unlimited")){
                    solveUnlimited(clues,investigationOptional.get(),investigation);
                }else{
                    solveSingle(clues, investigation, investigationOptional);
                }
            }else {
                log.warn("Could not find investigation for {}",investigation.getInvestigation());
            }
        }
        return clues;
    }

    private void solveSingle(Set<String> clues, Investigation investigation, Optional<InvestigationStrategy> investigationOptional) {
        String answer = investigationOptional.get().solve(investigation.getInvestigationParameters());
        Investigation investigationAnswer = restService.solveSingle(investigation.getId(),answer);
        clues.add(investigationAnswer.getOutcome());
        log.info("Solved investigation {} with clue {}",investigationAnswer.getInvestigation(),investigationAnswer.getOutcome());
    }

    private void solveUnlimited(Set<String> clues,InvestigationStrategy investigationStrategy, Investigation investigation) {
        Investigation investigationAnswer=null;
        if(investigationStrategy instanceof AncientAlgorithmInvestigation) {
            for (int i = 0; i < 27; i++) {
               String answer = investigationStrategy.solve(String.format("{\"cipher\":\"%s\",\"shift\":\"%d\"}",investigation.getInvestigationParameters(),i));
               investigationAnswer= restService.solveMultiple(investigation.getId(),answer);
                if(investigationAnswer !=null){
                    break;
                }
            }
            clues.add(investigationAnswer.getOutcome());
            log.info("Solved investigation {} with clue {}",investigationAnswer.getInvestigation(),investigationAnswer.getOutcome());
        }
    }

    private Optional<InvestigationStrategy> getInvestigation(String investigationString) {
        return investigationStrategyStrategies.stream().filter(investigation1 -> investigation1.getInvestigationName().equals(investigationString)).findFirst();
    }
}
