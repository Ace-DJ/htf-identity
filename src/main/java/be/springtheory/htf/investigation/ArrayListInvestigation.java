package be.springtheory.htf.investigation;

import be.springtheory.htf.investigation.Parameters.ArrayListParameters;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ArrayListInvestigation implements InvestigationStrategy {
    final private Gson gson;

    public ArrayListInvestigation(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String solve(String investigationParameters) {
        String params = investigationParameters.replaceAll("\"\\[","[")
                .replaceAll("]\"","]");
        ArrayListParameters arrayListParameters = gson.fromJson(params, ArrayListParameters.class);
        ArrayList<String> returnValue = arrayListParameters.getArrayList();
        returnValue.set(arrayListParameters.getIndex(), arrayListParameters.getReplacement());
        return returnValue.toString();
    }

    @Override
    public String getInvestigationName() {
        return "Replace string at given index in arraylist";
    }
}
