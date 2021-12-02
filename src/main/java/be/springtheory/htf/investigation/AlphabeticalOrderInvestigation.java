package be.springtheory.htf.investigation;

import org.springframework.stereotype.Component;

@Component
public class AlphabeticalOrderInvestigation implements InvestigationStrategy {

    @Override
    public String solve(String investigationParameters) {
        char lastChar = 0;
        for (char thisChar : investigationParameters.toCharArray()) {
            if(thisChar<lastChar) {
                return "N";
            }
            lastChar=thisChar;
        }
        return "Y";
    }

    @Override
    public String getInvestigationName() {
        return "Is the following string in alphabetical order? (Use N or Y as answer)";
    }
}
