package be.springtheory.htf.investigation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvestigationStrategyTests {

    @Autowired
    private List<InvestigationStrategy> investigationStrategyList;

    private InvestigationStrategy getInvestigation(String investigationString) {
       return investigationStrategyList.stream().filter(investigation1 -> investigation1.getInvestigationName().equals(investigationString)).findFirst().get();
    }

    @Test
    public void testDecode(){
        String investigationString = "Decode the following string";
        InvestigationStrategy investigationStrategy = getInvestigation(investigationString);
        String solved = investigationStrategy.solve("-. ..- .-- .-.. -.- -..- -..- -. .-.. ..- -.");
        assertEquals("nuwlkxxnlun",solved);
    }

    @Test
    public void testDisarium(){
        String investigationString = "Is the following number a Disarium Number? (Use N or Y as answer)";
        InvestigationStrategy investigationStrategy = getInvestigation(investigationString);
        String solved = investigationStrategy.solve("135");
        assertEquals("Y",solved);
        solved = investigationStrategy.solve("134");
        assertEquals("N",solved);
    }

    @Test
    public void reverseTest() {
        String investigationString = "Reverse the following String";
        InvestigationStrategy investigationStrategy = getInvestigation(investigationString);
        String solved = investigationStrategy.solve("test");
        assertEquals("tset",solved);
    }

    @Test
    public void primesTest() {
        String investigationString = "Find all the primes between the start and end index (both inclusive)";
        InvestigationStrategy investigation = getInvestigation(investigationString);
        String solved = investigation.solve("{\"start\":4831,\"end\":4900}");
        assertEquals("[4831,4861,4871,4877,4889]",solved);
    }

    @Test
    public void alphabeticalOrderTest() {
        String investigationString = "Is the following string in alphabetical order? (Use N or Y as answer)";
        InvestigationStrategy investigation = getInvestigation(investigationString);
        String solved = investigation.solve("0066AAEEabc");
        assertEquals("Y",solved);

        String wrongSolved = investigation.solve("251ddaA");
        assertEquals("N",wrongSolved);
    }

    @Test
    public void arrayListTest() {
        String investigationString = "Replace string at given index in arraylist";
        InvestigationStrategy investigation = getInvestigation(investigationString);
        String solved = investigation.solve("{\"index\":\"3\",\"arrayList\":\"[0KU, h6Ev, 6IG, rhi, xN4v, thoq, gUWf, v7It, HI3, 5kI]\",\"replacement\":\"HGwp\"}");
        assertEquals("[0KU, h6Ev, 6IG, HGwp, xN4v, thoq, gUWf, v7It, HI3, 5kI]",solved);
    }

    @Test
    public void perfectNumberTest() {
        String investigationString = "What is the Perfect Number in the nth position (1-based)?";
        InvestigationStrategy investigation = getInvestigation(investigationString);
        String solved = investigation.solve("\"{\"nth element\":\"3\"}\"");
        assertEquals("496",solved);
    }

    @Test
    public void firstLastDayTest(){
        String investigationString = "Answer is the first and last day of the month (MONDAY - SUNDAY) eg: MONDAY-FRIDAY";;
        InvestigationStrategy investigation = getInvestigation(investigationString);
        String solved = investigation.solve("{\"month\":\"6\",\"year\":\"1995\"}");
        assertEquals("THURSDAY-FRIDAY",solved);
    }

    @Test
    public void ancientAlgorithmTest(){
        String investigationString = "The following string is encrypted with an ancient algorithm, invented in one of the mightiest empires of all time.Tip: one of the leaders of this empire.";
        InvestigationStrategy investigation = getInvestigation(investigationString);
        String solved = investigation.solve("{\"cipher\":\"khoor zruog\",\"shift\":\"3\"}");
        assertEquals("hello world",solved);
    }

}
