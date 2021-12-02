package be.springtheory.htf.investigation;

import be.springtheory.htf.investigation.Parameters.PrimeNumberParameters;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class PrimeNumberInvestigation implements InvestigationStrategy {
    private Gson gson;

    public PrimeNumberInvestigation(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String solve(String investigationParameters) {
        PrimeNumberParameters primeNumberParameters = gson.fromJson(investigationParameters, PrimeNumberParameters.class);
        List<Integer> answer = new ArrayList<>();
        for (int i = primeNumberParameters.getStart(); i <= primeNumberParameters.getEnd(); i++) {
            if (checkForPrime(i)) {
                answer.add(i);
            }
        }
        return answer.toString().replace(" ", "");
    }

    @Override
    public String getInvestigationName() {
        return "Find all the primes between the start and end index (both inclusive)";
    }

    static boolean checkForPrime(int inputNumber) {
        boolean isItPrime = true;

        if (inputNumber <= 1) {
            isItPrime = false;
        } else {
            for (int i = 2; i < inputNumber / 2; i++) {
                if ((inputNumber % i) == 0) {
                    isItPrime = false;
                    break;
                }
            }
        }
        return isItPrime;
    }
}
