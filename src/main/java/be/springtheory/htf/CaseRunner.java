package be.springtheory.htf;

import be.springtheory.htf.service.CaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CaseRunner implements CommandLineRunner {
    private final CaseService caseService;

    public CaseRunner(CaseService caseService) {
        this.caseService = caseService;
    }


    @Override
    public void run(String... args) throws Exception {
    }
}
