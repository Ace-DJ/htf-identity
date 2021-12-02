package be.springtheory.htf.investigation;

import org.springframework.stereotype.Component;

@Component
public class ReverseStringInvestigation implements Investigation {

    @Override
    public String solve(String investigationParameters) {
        return new StringBuilder(investigationParameters).reverse().toString();
    }

    @Override
    public String getInvestigationName() {
        return "Reverse the following String";
    }
}
