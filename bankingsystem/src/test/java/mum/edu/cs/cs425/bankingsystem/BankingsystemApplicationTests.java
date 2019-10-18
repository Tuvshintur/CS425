package mum.edu.cs.cs425.bankingsystem;

import mum.edu.cs.cs425.bankingsystem.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BankingsystemApplicationTests {

    @Autowired
    private AccountService accountService;

    @Test
    void testComputeNetLiquidity() {
        double actual = accountService.computeNetLiquidity();
        double excepted = 484596.21;
        assertEquals(excepted, actual,0.10);
    }

}
