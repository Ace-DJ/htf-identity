package be.springtheory.htf.investigation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InvestigationTests {

    @Autowired
    private List<Investigation> investigationList;

    private Investigation getInvestigation(String investigationString) {
       return investigationList.stream().filter(investigation1 -> investigation1.getInvestigationName().equals(investigationString)).findFirst().get();
    }

    @Test
    public void testDecode(){
        String investigationString = "Decode the following string";
        Investigation investigation = getInvestigation(investigationString);
        String solved = investigation.solve("-. ..- .-- .-.. -.- -..- -..- -. .-.. ..- -.");
        assertEquals("n u w l k x x n l u n",solved);
    }

    @Test
    public void testDisarium(){
        String investigationString = "Is the following number a Disarium Number? (Use N or Y as answer)";
        Investigation investigation = getInvestigation(investigationString);
        String solved = investigation.solve("135");
        assertEquals("Y",solved);
        solved = investigation.solve("134");
        assertEquals("N",solved);
    }

    @Test
    public void reverseTest() {
        String investigationString = "Reverse the following String";
        Investigation investigation = getInvestigation(investigationString);
        String solved = investigation.solve("test");
        assertEquals("tset",solved);
    }

}
