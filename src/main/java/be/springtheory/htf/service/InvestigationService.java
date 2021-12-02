package be.springtheory.htf.service;

import be.springtheory.htf.investigation.InvestigationStrategy;
import be.springtheory.htf.model.Investigation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    private void solveInvestigations(List<Investigation> investigationStrategyList){
        for (final Investigation investigation : investigationStrategyList) {
            var investigationOptional = getInvestigation(investigation.getInvestigation());
            if(investigationOptional.isPresent()) {
                String answer = investigationOptional.get().solve(investigation.getInvestigationParameters());
               Investigation investigationAnswer = restService.solveInvestigation(investigation.getId(),answer);
               log.info(investigationAnswer.toString());
            }
        }
    }

    private Optional<InvestigationStrategy> getInvestigation(String investigationString) {
        return investigationStrategyStrategies.stream().filter(investigation1 -> investigation1.getInvestigationName().equals(investigationString)).findFirst();
    }
}
