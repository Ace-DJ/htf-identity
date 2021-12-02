package be.springtheory.htf.service;

import be.springtheory.htf.investigation.Investigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InvestigationService {
    private final Map<String, Investigation> investigationStrategies;

    @Autowired
    public InvestigationService(Map<String, Investigation> investigationStrategies) {
        this.investigationStrategies = investigationStrategies;
    }
}
