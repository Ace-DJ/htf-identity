package be.springtheory.htf.investigation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DisariumInvestigationTest {
    @Autowired
    private DisariumInvestigation disariumInvestigation;

    @Test
    public void testSolve(){
        String solved = disariumInvestigation.solve("135");
        assertEquals("Y",solved);
        solved = disariumInvestigation.solve("134");
        assertEquals("N",solved);
    }

}