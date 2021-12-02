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
        assertEquals("n u w l k x x n l u n",solved);
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
        String solved = investigation.solve("{\"start\":2,\"end\":23}");
        assertEquals("2, 3, 4, 5, 7, 11, 13, 17, 19, 23",solved);
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

}
