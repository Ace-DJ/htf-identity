package be.springtheory.htf.investigation;

import be.springtheory.htf.investigation.Parameters.MissingNumbersParameters;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MissingNumberInvestigation implements InvestigationStrategy {
    private Gson gson;

    public MissingNumberInvestigation(Gson gson) {
        this.gson = gson;
    }


    @Override
    public String solve(String challengeParameters) {
        String[] sequence = challengeParameters.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");

        int[] results = new int[sequence.length];

        for (int i = 0; i < sequence.length; i++) {
            results[i] = Integer.parseInt(sequence[i]);
        }
        StringBuilder stringBuilder = new StringBuilder();
        int num1 = 0, num2 = 1, mistakes = 0;

        for (int i = 0; i < results.length; i++) {
            if (num1 > Arrays.stream(results).max().orElse(0)) {
                num1 = 0;
                num2 = 1;
            }
            if (num1 != results[i - mistakes]) {
                stringBuilder.append(num1).append(",");
                mistakes++;
            }

            int sumOfPrevTwo = num1 + num2;
            num1 = num2;
            num2 = sumOfPrevTwo;

        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    @Override
    public String getInvestigationName() {
        return "Find the missing numbers in the sequence. Return them as a comma-separated string";
    }


}
