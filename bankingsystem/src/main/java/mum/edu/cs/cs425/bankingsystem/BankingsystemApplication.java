package mum.edu.cs.cs425.bankingsystem;

import mum.edu.cs.cs425.bankingsystem.model.AccountType;
import mum.edu.cs.cs425.bankingsystem.repository.AccountRepository;
import mum.edu.cs.cs425.bankingsystem.repository.AccountTypeRepository;
import mum.edu.cs.cs425.bankingsystem.service.impl.AccountTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingsystemApplication implements CommandLineRunner {

    private AccountTypeRepository accountTypeRepository;

    public BankingsystemApplication(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BankingsystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello Banking System!!!");
        if (accountTypeRepository.count() == 0) {
            accountTypeRepository.save(new AccountType("Checking"));
            accountTypeRepository.save(new AccountType("Loan"));
            accountTypeRepository.save(new AccountType("Saving"));
        }
    }
}
