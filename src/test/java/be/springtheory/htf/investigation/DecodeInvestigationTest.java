package be.springtheory.htf.investigation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DecodeInvestigationTest {

    @Autowired
    DecodeInvestigation decodeInvestigation;
    @Test
    public void testSolve(){
        String solved = decodeInvestigation.solve("-. ..- .-- .-.. -.- -..- -..- -. .-.. ..- -.");
        assertEquals(solved,"n u w l k x x n l u n");
    }

}