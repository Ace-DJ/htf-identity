package be.springtheory.htf.investigation;

import be.springtheory.htf.investigation.Parameters.ArrayListParameters;
import be.springtheory.htf.investigation.Parameters.FirstLastDayParameters;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FirstLastDayInvestigation implements InvestigationStrategy{
    final private Gson gson;

    public FirstLastDayInvestigation(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String solve(String investigationParameters) {
        FirstLastDayParameters firstLastDayParameters = gson.fromJson(investigationParameters, FirstLastDayParameters.class);
        StringBuilder returnValue = new StringBuilder();
        LocalDate localDate = LocalDate.now().withMonth(firstLastDayParameters.getMonth()).withYear(firstLastDayParameters.getYear());
        LocalDate start = localDate.withDayOfMonth(1);
        LocalDate end = localDate.withDayOfMonth(localDate.lengthOfMonth());
        returnValue.append(start.getDayOfWeek()).append("-").append(end.getDayOfWeek());
        return returnValue.toString();
    }

    @Override
    public String getInvestigationName() {
        return "Answer is the first and last day of the month (MONDAY - SUNDAY) eg: MONDAY-FRIDAY";
    }
}
