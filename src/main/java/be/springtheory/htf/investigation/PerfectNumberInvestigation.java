package be.springtheory.htf.investigation;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PerfectNumberInvestigation implements InvestigationStrategy{
    @Override
    public String solve(String challengeParameters) {
        int nth = Integer.parseInt(challengeParameters.replaceAll("[^0-9]+",""));
        ArrayList<Integer> perfnums = new ArrayList<>();

        for (int n = 2; n < 1000000; n++) {
            if (isPerfect(n)) {
                perfnums.add(n);
                if (perfnums.size() == nth) {
                    return perfnums.get(perfnums.size() - 1).toString();
                }
            }
        }

        return "1";
    }

    static boolean isPerfect(int n) {
        int sum = 1;

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                if (i * i != n)
                    sum = sum + i + n / i;
                else
                    sum = sum + i;
            }
        }

        return sum == n && n != 1;
    }


    @Override
    public String getInvestigationName() {
        return "What is the Perfect Number in the nth position (1-based)?";
    }
}
