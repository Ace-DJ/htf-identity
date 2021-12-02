package be.springtheory.htf.investigation;

import be.springtheory.htf.investigation.Parameters.AncientParameters;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class AncientAlgorithmInvestigation implements InvestigationStrategy{
    final private Gson gson;

    public AncientAlgorithmInvestigation(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String solve(String investigationParameters) {
        final String params = investigationParameters.replaceAll("\"\\[","[")
                .replaceAll("]\"","]");
        final AncientParameters ancientParameters = gson.fromJson(params,AncientParameters.class);
        final String cipher = ancientParameters.getCipher();
        final int shift = ancientParameters.getShift();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < cipher.length(); i++) {
            if (Character.isUpperCase(cipher.charAt(i))) {
                char ch = (char) (((int) cipher.charAt(i) +
                        shift - 65) % 26 + 65);
                result.append(ch);
            } else {
                char ch = (char) (((int) cipher.charAt(i) +
                        shift - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result.toString();
    }

    @Override
    public String getInvestigationName() {
        return "The following string is encrypted with an ancient algorithm, invented in one of the mightiest empires of all time.Tip: one of the leaders of this empire.";
    }
}
