package mum.edu.cs.cs425.etdm;

import mum.edu.cs.cs425.etdm.service.AthleteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EtdmApplicationTests {

    @Autowired
    private AthleteService athleteService;

    @Test
    void testCountEliteAthletes() {
        int actual = athleteService.countEliteAthletes();
        int excepted = 2;
        assertEquals(excepted, actual);
    }
}
