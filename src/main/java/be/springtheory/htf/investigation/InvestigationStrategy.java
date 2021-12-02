package be.springtheory.htf.investigation;

public interface InvestigationStrategy {
    String solve(String investigationParameters);

    String getInvestigationName();
}
