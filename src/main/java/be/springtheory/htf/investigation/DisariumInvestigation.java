package be.springtheory.htf.investigation;

import org.springframework.stereotype.Component;

@Component
public class DisariumInvestigation implements InvestigationStrategy {

    @Override
    public String solve(String investigationParameters) {
        int num = Integer.parseInt(investigationParameters);
        int orgNum = num;
        int digitCount = 0;

        while (num != 0) {
            num /= 10;
            digitCount++;
        }

        num = orgNum;
        int sum = 0;

        while (num != 0) {
            int d = num % 10;
            sum += Math.pow(d, digitCount);
            digitCount--;
            num /= 10;
        }

        if (sum == orgNum)
            return "Y";
        else
            return "N";
    }

    @Override
    public String getInvestigationName() {
        return "Is the following number a Disarium Number? (Use N or Y as answer)";
    }
}
