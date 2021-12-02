package be.springtheory.htf.investigation;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.Arrays;


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
        int num1 = 0, num2 = 1, offset = 0;

        for (int i = 0; i + offset < results.length; i++) {
            if (num1 != results[i - offset]) {
                stringBuilder.append(num1).append(",");
                offset++;
            }
            if (num2 > Arrays.stream(results).max().orElse(0)) {
                num1 = 0;
                num2 = 1;
            }else{
                int sumOfPrevTwo = num1 + num2;
                num1 = num2;
                num2 = sumOfPrevTwo;
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    @Override
    public String getInvestigationName() {
        return "Find the missing numbers in the sequence. Return them as a comma-separated string";
    }


}
